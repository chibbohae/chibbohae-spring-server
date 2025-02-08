package sentosa.sentosaspringserver.global.security.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientLoginRequestDto(
	@NotBlank String loginId,
	@NotBlank String loginPassword
) {}
