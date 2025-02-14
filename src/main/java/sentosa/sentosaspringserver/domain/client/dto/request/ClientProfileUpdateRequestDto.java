package sentosa.sentosaspringserver.domain.client.dto.request;

public record ClientProfileUpdateRequestDto(
	String university,
	String major,
	String interest
) {
}
