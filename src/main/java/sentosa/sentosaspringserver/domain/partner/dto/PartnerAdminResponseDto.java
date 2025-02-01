package sentosa.sentosaspringserver.domain.partner.dto;

import sentosa.sentosaspringserver.global.entity.Gender;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;

public record PartnerAdminResponseDto(
	String loginId,
	String name,
	Integer age,
	Gender gender,
	String telephone,
	String company,
	Integer yearsOfExperience,
	String position,
	String bio
) {
	public PartnerAdminResponseDto(Partner partner) {
		this(
			partner.getLoginId(),
			partner.getName(),
			partner.getAge(),
			partner.getGender(),
			partner.getTelephone(),
			partner.getCompany(),
			partner.getYearsOfExperience(),
			partner.getPosition(),
			partner.getBio()
		);
	}
}
