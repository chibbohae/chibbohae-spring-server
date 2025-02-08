package sentosa.sentosaspringserver.global.security.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sentosa.sentosaspringserver.global.security.auth.service.AuthService;
import sentosa.sentosaspringserver.global.security.auth.dto.request.ClientLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.ClientSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.PartnerLoginRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.request.PartnerSignupRequestDto;
import sentosa.sentosaspringserver.global.security.auth.dto.response.TokenResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "로그인 및 회원가입")
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "파트너 일반 회원가입")
	@PostMapping("/partner/signup")
	public ResponseEntity<TokenResponse> partnerSignup(@RequestBody @Validated PartnerSignupRequestDto signupRequestDto) {
		return ResponseEntity.ok(authService.partnerSignup(signupRequestDto));
	}

	@Operation(summary = "클라이언트 일반 회원가입")
	@PostMapping("/client/signup")
	public ResponseEntity<TokenResponse> clientSignup(@RequestBody @Validated ClientSignupRequestDto signupRequestDto) {
		return ResponseEntity.ok(authService.clientSignup(signupRequestDto));
	}

	@Operation(summary = "파트너 일반 로그인")
	@PostMapping("/partner/login")
	public ResponseEntity<TokenResponse> partnerLogin(@RequestBody @Validated PartnerLoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(authService.partnerLogin(loginRequestDto));
	}

	@Operation(summary = "클라이언트 일반 로그인")
	@PostMapping("/client/login")
	public ResponseEntity<TokenResponse> clientLogin(@RequestBody @Validated ClientLoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(authService.clientLogin(loginRequestDto));
	}
}
