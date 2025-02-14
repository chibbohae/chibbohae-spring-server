package sentosa.sentosaspringserver.global.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponse(
		String message,
		HttpStatus httpStatus
) {
}
