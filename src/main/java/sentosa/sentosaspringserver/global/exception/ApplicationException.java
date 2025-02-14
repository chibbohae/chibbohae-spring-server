package sentosa.sentosaspringserver.global.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
	private final ApplicationError applicationError;

	public ApplicationException(ApplicationError applicationExceptionMessage) {
		super(applicationExceptionMessage.getMessage());
		this.applicationError = applicationExceptionMessage;
	}

}
