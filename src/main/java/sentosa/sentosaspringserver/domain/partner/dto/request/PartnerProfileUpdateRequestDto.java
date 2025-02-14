package sentosa.sentosaspringserver.domain.partner.dto.request;


public record PartnerProfileUpdateRequestDto(
	String company,
	Integer yearsOfExperience,
	String bio,
	String position
) {
}
