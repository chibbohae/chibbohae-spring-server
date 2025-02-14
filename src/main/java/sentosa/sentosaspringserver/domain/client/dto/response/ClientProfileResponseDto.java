package sentosa.sentosaspringserver.domain.client.dto.response;

import sentosa.sentosaspringserver.domain.client.entity.Client;

public record ClientProfileResponseDto(
	String name,
	String university,
	Integer age,
	String major,
	String interest,
	String telephone,
	String email
) {
	public static ClientProfileResponseDto from(
		Client client
	) {
		return new ClientProfileResponseDto(
			client.getName(),
			client.getUniversity(),
			client.getAge(),
			client.getMajor(),
			client.getInterest(),
			client.getTelephone(),
			client.getEmail()
		);
	}
}
