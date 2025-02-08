package sentosa.sentosaspringserver.global.security.auth;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.domain.client.entity.Client;
import sentosa.sentosaspringserver.domain.client.service.ClientAdminService;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;
import sentosa.sentosaspringserver.domain.partner.service.PartnerAdminService;
import sentosa.sentosaspringserver.global.security.auth.dto.ClientLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.ClientSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.PartnerLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.PartnerSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.TokenResponse;
import sentosa.sentosaspringserver.global.security.auth.jwt.JwtTokenProvider;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PartnerAdminService partnerAdminService;
	private final ClientAdminService clientAdminService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	// ✅ Partner 회원가입
	@Transactional
	public TokenResponse partnerSignup(PartnerSignupRequestDto signupRequestDto) {
		Partner partner = partnerAdminService.createPartner(
			signupRequestDto.name(),
			signupRequestDto.age(),
			signupRequestDto.gender(),
			signupRequestDto.telephone(),
			signupRequestDto.email(),
			signupRequestDto.loginId(),
			passwordEncoder.encode(signupRequestDto.loginPassword()),
			signupRequestDto.company(),
			signupRequestDto.yearsOfExperience(),
			signupRequestDto.position(),
			signupRequestDto.bio()
		);

		return jwtTokenProvider.createTokenResponse(partner.getId(), partner.getName(), "ROLE_PARTNER");
	}

	// ✅ Client 회원가입
	@Transactional
	public TokenResponse clientSignup(ClientSignupRequestDto signupRequestDto) {
		Client client = clientAdminService.createClient(
			signupRequestDto.name(),
			signupRequestDto.age(),
			signupRequestDto.gender(),
			signupRequestDto.telephone(),
			signupRequestDto.email(),
			signupRequestDto.loginId(),
			passwordEncoder.encode(signupRequestDto.loginPassword()),
			signupRequestDto.interest(),
			signupRequestDto.major(),
			signupRequestDto.university()
		);

		return jwtTokenProvider.createTokenResponse(client.getId(), client.getName(), "ROLE_CLIENT");
	}

	// ✅ Partner 로그인
	@Transactional
	public TokenResponse partnerLogin(PartnerLoginRequestDto loginRequestDto) {
		Partner partner = partnerAdminService.findByLoginId(loginRequestDto.loginId())
			.orElseThrow(() -> new IllegalArgumentException("해당 로그인 ID를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(loginRequestDto.loginPassword(), partner.getLoginPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return jwtTokenProvider.createTokenResponse(partner.getId(), partner.getName(), "ROLE_PARTNER");
	}

	// ✅ Client 로그인
	@Transactional
	public TokenResponse clientLogin(ClientLoginRequestDto loginRequestDto) {
		Client client = clientAdminService.findByLoginId(loginRequestDto.loginId())
			.orElseThrow(() -> new IllegalArgumentException("해당 로그인 ID를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(loginRequestDto.loginPassword(), client.getLoginPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return jwtTokenProvider.createTokenResponse(client.getId(), client.getName(), "ROLE_CLIENT");
	}
}
