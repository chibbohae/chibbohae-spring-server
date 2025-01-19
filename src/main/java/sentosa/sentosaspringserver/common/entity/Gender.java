package sentosa.sentosaspringserver.common.entity;

public enum Gender {
	M("Male"),    // 남성
	F("Female"),  // 여성
	O("Other");   // 기타

	private final String description;

	Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
