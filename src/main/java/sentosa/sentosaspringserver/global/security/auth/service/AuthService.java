package sentosa.sentosaspringserver.global.security.auth.service;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.domain.client.entity.Client;
import sentosa.sentosaspringserver.domain.client.service.ClientService;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;
import sentosa.sentosaspringserver.domain.partner.service.PartnerService;
import sentosa.sentosaspringserver.global.security.auth.dto.request.ClientLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.ClientSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.PartnerLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.PartnerSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.response.TokenResponse;
import sentosa.sentosaspringserver.global.security.auth.jwt.JwtTokenProvider;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PartnerService partnerService;
	private final ClientService clientService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public TokenResponse partnerSignup(PartnerSignupRequestDto signupRequestDto) {
		Partner partner = partnerService.createPartner(
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

	@Transactional
	public TokenResponse clientSignup(ClientSignupRequestDto signupRequestDto) {
		Client client = clientService.createClient(
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

	@Transactional
	public TokenResponse partnerLogin(PartnerLoginRequestDto loginRequestDto) {
		Partner partner = partnerService.findByLoginId(loginRequestDto.loginId())
			.orElseThrow(() -> new IllegalArgumentException("해당 로그인 ID를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(loginRequestDto.loginPassword(), partner.getLoginPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return jwtTokenProvider.createTokenResponse(partner.getId(), partner.getName(), "ROLE_PARTNER");
	}

	@Transactional
	public TokenResponse clientLogin(ClientLoginRequestDto loginRequestDto) {
		Client client = clientService.findByLoginId(loginRequestDto.loginId())
			.orElseThrow(() -> new IllegalArgumentException("해당 로그인 ID를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(loginRequestDto.loginPassword(), client.getLoginPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return jwtTokenProvider.createTokenResponse(client.getId(), client.getName(), "ROLE_CLIENT");
	}
}
