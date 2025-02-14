package sentosa.sentosaspringserver.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BusinessError {
	// Partner
	PARTNER_NOT_FOUND("해당 파트너를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	PARTNER_DUPLICATE_LOGIN_ID("이미 사용중인 로그인 아이디입니다.", HttpStatus.CONFLICT),
	PARTNER_DUPLICATE_EMAIL("이미 사용중인 이메일입니다.", HttpStatus.CONFLICT ),
	PARTNER_DUPLICATE_TELEPHONE("이미 사용중인 전화번호입니다.", HttpStatus.CONFLICT ),

	// Client
	CLIENT_DUPLICATE_LOGIN_ID("이미 사용중인 로그인 아이디입니다.", HttpStatus.CONFLICT),
	CLIENT_DUPLICATE_EMAIL("이미 사용중인 이메일입니다.", HttpStatus.CONFLICT ),
	CLIENT_DUPLICATE_TELEPHONE("이미 사용중인 전화번호입니다.", HttpStatus.CONFLICT ),;


	private final String message;
	private final HttpStatus httpStatus;

	BusinessError(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
