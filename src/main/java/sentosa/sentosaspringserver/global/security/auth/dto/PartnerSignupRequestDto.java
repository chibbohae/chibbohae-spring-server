package sentosa.sentosaspringserver.global.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import sentosa.sentosaspringserver.global.entity.Gender;

public record PartnerSignupRequestDto(
	@NotBlank String name,
	Integer age,
	Gender gender,

	@NotBlank @Size(max = 15) String telephone,

	@NotBlank @Email String email,

	@NotBlank String loginId,

	@NotBlank String loginPassword,

	@NotBlank String company,

	Integer yearsOfExperience,

	@NotBlank String position,

	String bio
) {}
