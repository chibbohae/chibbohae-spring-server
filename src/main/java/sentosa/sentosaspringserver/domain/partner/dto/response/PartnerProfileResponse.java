package sentosa.sentosaspringserver.domain.partner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;

@Getter
@AllArgsConstructor
public class PartnerProfileResponse {
	private String name;
	private String company;
	private Integer yearsOfExperience;
	private Integer age;
	private String bio;
	private String position;
	private String email;
	private String telephone;

	public static PartnerProfileResponse of(Partner partner) {
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
