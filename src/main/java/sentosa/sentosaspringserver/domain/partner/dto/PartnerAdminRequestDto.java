package sentosa.sentosaspringserver.domain.partner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import sentosa.sentosaspringserver.common.entity.Gender;

public record PartnerAdminRequestDto(
	@NotBlank(message = "Login ID는 필수입니다.")
	String loginId,

	@NotBlank(message = "Login Password는 필수입니다.")
	String loginPassword,

	@NotBlank(message = "Name은 필수입니다.")
	String name,

	@NotNull(message = "Age는 필수입니다.")
	@Positive(message = "Age는 0 이상이어야 합니다.") // 양수만 허용
	Integer age,

	@NotNull(message = "Gender는 필수입니다.")
	Gender gender,

	@NotBlank(message = "Telephone은 필수입니다.")
	@Size(max = 15, message = "Telephone은 최대 15자까지 가능합니다.")
	@Pattern(
		regexp = "\\d{2,3}-\\d{3,4}-\\d{4}",
		message = "Telephone 형식이 올바르지 않습니다. (예: 010-1234-5678)"
	)
	String telephone,

	String company,

	@Positive(message = "연차는 0 이상이어야 합니다.") // 음수를 허용하지 않음
	Integer yearsOfExperience,

	String position,
	String bio
) {}
