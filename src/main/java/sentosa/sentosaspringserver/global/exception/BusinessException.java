package sentosa.sentosaspringserver.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final BusinessError businessError;

	public BusinessException(BusinessError businessError) {
		super(businessError.getMessage());
		this.businessError = businessError;
	}

}
