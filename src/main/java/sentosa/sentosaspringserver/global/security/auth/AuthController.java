package sentosa.sentosaspringserver.global.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sentosa.sentosaspringserver.global.security.auth.dto.ClientLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.ClientSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.PartnerLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.PartnerSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.TokenResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final AuthService authService;

	// ✅ Partner 회원가입 API
	@PostMapping("/partner/signup")
	public ResponseEntity<TokenResponse> partnerSignup(@RequestBody @Validated PartnerSignupRequestDto signupRequestDto) {
		return ResponseEntity.ok(authService.partnerSignup(signupRequestDto));
	}

	// ✅ Client 회원가입 API
	@PostMapping("/client/signup")
	public ResponseEntity<TokenResponse> clientSignup(@RequestBody @Validated ClientSignupRequestDto signupRequestDto) {
		return ResponseEntity.ok(authService.clientSignup(signupRequestDto));
	}

	// ✅ Partner 로그인 API
	@PostMapping("/partner/login")
	public ResponseEntity<TokenResponse> partnerLogin(@RequestBody @Validated PartnerLoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(authService.partnerLogin(loginRequestDto));
	}

	// ✅ Client 로그인 API
	@PostMapping("/client/login")
	public ResponseEntity<TokenResponse> clientLogin(@RequestBody @Validated ClientLoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(authService.clientLogin(loginRequestDto));
	}
}
