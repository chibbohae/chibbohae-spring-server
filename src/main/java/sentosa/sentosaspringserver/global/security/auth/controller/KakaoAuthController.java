package sentosa.sentosaspringserver.global.security.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.global.security.auth.dto.response.TokenResponse;
import sentosa.sentosaspringserver.global.security.auth.service.KakaoOAuthService;

@RestController
@RequestMapping("/v1/auth/kakao")
@RequiredArgsConstructor
@Tag(name = "카카오 로그인 및 회원가입")
public class KakaoAuthController {
	private final KakaoOAuthService kakaoOAuthService;

	@Operation(summary = "[파트너] 카카오 로그인")
	@GetMapping("/partner/callback")
	public ResponseEntity<TokenResponse> kakaoPartnerCallback(@RequestParam String code) {
		return ResponseEntity.ok(kakaoOAuthService.kakaoLogin(code, "PARTNER"));
	}

	@Operation(summary = "[클라이언트] 카카오 로그인")
	@GetMapping("/client/callback")
	public ResponseEntity<TokenResponse> kakaoClientCallback(@RequestParam String code) {
		return ResponseEntity.ok(kakaoOAuthService.kakaoLogin(code, "CLIENT"));
	}
}
