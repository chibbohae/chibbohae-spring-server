package sentosa.sentosaspringserver.global.security.auth.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.domain.client.entity.Client;
import sentosa.sentosaspringserver.domain.client.service.ClientService;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;
import sentosa.sentosaspringserver.domain.partner.service.PartnerService;
import sentosa.sentosaspringserver.global.entity.KakaoUserInfo;
import sentosa.sentosaspringserver.global.security.auth.dto.response.TokenResponse;
import sentosa.sentosaspringserver.global.security.auth.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class KakaoOAuthService {
	private final RestTemplate restTemplate;
	private final JwtTokenProvider jwtTokenProvider;
	private final PartnerService partnerService;
	private final ClientService clientService;

	@Value("${kakao.client-id}")
	private String kakaoClientId;

	@Value("${kakao.redirect-uri.partner}")
	private String kakaoPartnerRedirectUri;

	@Value("${kakao.redirect-uri.client}")
	private String kakaoClientRedirectUri;

	private static final String KAKAO_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
	private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

	public TokenResponse kakaoLogin(String code, String userType) {
		String redirectUri = userType.equals("PARTNER") ? kakaoPartnerRedirectUri : kakaoClientRedirectUri;

		// 1️⃣ 인가 코드로 액세스 토큰 요청
		String accessToken = getKakaoAccessToken(code, redirectUri);

		// 2️⃣ 액세스 토큰으로 사용자 정보 요청
		KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(accessToken);

		// 3️⃣ Partner 또는 Client인지에 따라 회원가입 or 로그인 처리
		return userType.equals("PARTNER")
			? createPartnerToken(kakaoUserInfo)
			: createClientToken(kakaoUserInfo);
	}

	private String getKakaoAccessToken(String code, String redirectUri) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", redirectUri);
		params.add("code", code);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<Map> response = restTemplate.postForEntity(KAKAO_TOKEN_URI, request, Map.class);

		if (response.getBody() == null || response.getBody().get("access_token") == null) {
			throw new RuntimeException("카카오 액세스 토큰 요청 실패");
		}

		return (String) response.getBody().get("access_token");
	}

	private KakaoUserInfo getKakaoUserInfo(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);

		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<Map> response = restTemplate.exchange(KAKAO_USER_INFO_URI, HttpMethod.GET, request, Map.class);

		Map<String, Object> kakaoAccount = (Map<String, Object>) response.getBody().get("kakao_account");

		if (kakaoAccount == null) {
			throw new RuntimeException("카카오 사용자 정보를 가져오지 못했습니다.");
		}

		String email = (String) kakaoAccount.get("email");
		String nickname = (String) ((Map<String, Object>) kakaoAccount.get("profile")).get("nickname");

		return new KakaoUserInfo(email, nickname);
	}

	private TokenResponse createPartnerToken(KakaoUserInfo kakaoUserInfo) {
		Partner partner = partnerService.findByEmail(kakaoUserInfo.getEmail())
			.orElseGet(() -> partnerService.createPartnerFromKakao(kakaoUserInfo));

		return jwtTokenProvider.createTokenResponse(partner.getId(), partner.getName(), "ROLE_PARTNER");
	}

	private TokenResponse createClientToken(KakaoUserInfo kakaoUserInfo) {
		Client client = clientService.findByEmail(kakaoUserInfo.getEmail())
			.orElseGet(() -> clientService.createClientFromKakao(kakaoUserInfo));

		return jwtTokenProvider.createTokenResponse(client.getId(), client.getName(), "ROLE_CLIENT");
	}
}
