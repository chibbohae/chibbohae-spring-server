package sentosa.sentosaspringserver.domain.client.dto;

import sentosa.sentosaspringserver.domain.client.entity.Client;

public record ClientResponseDto(
	String loginId,
	String loginPassword,
	String name,
	Integer age,
	String email,
	String telephone,
	String interest,
	String major,
	String university
) {
	public ClientResponseDto(Client client) {
		this(
			client.getLoginId(),
			client.getLoginPassword(),
			client.getName(),
			client.getAge(),
			client.getEmail(),
			client.getTelephone(),
			client.getInterest(),
			client.getMajor(),
			client.getUniversity()
		);
	}
}
