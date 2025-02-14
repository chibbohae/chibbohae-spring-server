package sentosa.sentosaspringserver.domain.partner.dto.response;

import sentosa.sentosaspringserver.domain.partner.entity.Partner;

public record PartnerProfileResponse (
	String name,
	String company,
	Integer yearsOfExperience,
	Integer age,
	String bio,
	String position,
	String email,
	String telephone
) {

	public static PartnerProfileResponse from(Partner partner) {
		return new PartnerProfileResponse(
			partner.getName(),
			partner.getCompany(),
			partner.getYearsOfExperience(),
			partner.getAge(),
			partner.getBio(),
			partner.getPosition(),
			partner.getEmail(),
			partner.getTelephone()
		);
	}
}
