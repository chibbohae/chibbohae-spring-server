package sentosa.sentosaspringserver.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	DUPLICATE_LOGIN_ID("이미 존재하는 로그인 아이디입니다.");

	private final String message;
}
