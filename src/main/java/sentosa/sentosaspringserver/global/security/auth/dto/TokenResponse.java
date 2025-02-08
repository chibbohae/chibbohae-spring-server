package sentosa.sentosaspringserver.global.security.auth.dto;

import lombok.Builder;

@Builder
public record TokenResponse(
		String accessToken,
		String refreshToken
) {
}
