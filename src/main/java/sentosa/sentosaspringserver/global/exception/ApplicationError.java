package sentosa.sentosaspringserver.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApplicationError {
	// JWT
	JWT_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),

	// Authentication
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

	// AI ChatBot
	AI_CHATBOT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AI ChatBot 서비스 오류입니다.");


	private final HttpStatus httpStatus;
	private final String message;

	ApplicationError(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
}
