package sentosa.sentosaspringserver.domain.client.dto;

import sentosa.sentosaspringserver.global.entity.Gender;

public record ClientRequestDto(
	String loginId,
	String loginPassword,
	String name,
	Integer age,
	String email,
	Gender gender,
	String telephone,
	String company,
	String interest,
	String major,
	String university
) {
}
