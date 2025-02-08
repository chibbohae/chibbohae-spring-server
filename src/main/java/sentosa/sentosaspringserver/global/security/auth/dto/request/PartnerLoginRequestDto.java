package sentosa.sentosaspringserver.global.security.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PartnerLoginRequestDto(
	@NotBlank String loginId,
	@NotBlank String loginPassword
) {}
