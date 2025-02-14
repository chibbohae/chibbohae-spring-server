package sentosa.sentosaspringserver.global.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(e.getApplicationError().getHttpStatus())
				.body(new ExceptionResponse(e.getApplicationError().getMessage(), e.getApplicationError().getHttpStatus()));

	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(e.getBusinessError().getHttpStatus())
				.body(new ExceptionResponse(e.getBusinessError().getMessage(), e.getBusinessError().getHttpStatus()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return ResponseEntity.status(500)
				.body(new ExceptionResponse("알 수 없는 오류가 발생했습니다.", null));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException e) {
		// 여러 필드에 대한 오류 메시지를 하나의 문자열로 조합
		String errors = e.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
				.collect(Collectors.joining(", "));

		// 기존 메시지에 추가
		String message = "Validation failed: " + errors;

		return ResponseEntity.badRequest()
				.body(new ExceptionResponse(message, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String errors = e.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.joining(", "));

		// 메시지에 추가
		String message = "Validation failed: " + errors;

		return ResponseEntity.badRequest()
				.body(new ExceptionResponse(message, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		// 단일 누락된 파라미터 정보만 메시지에 추가
		String message = "Required parameter missing: " + e.getParameterName();

		return ResponseEntity.badRequest()
				.body(new ExceptionResponse(message, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		String message = "Invalid argument: " + e.getMessage();

		return ResponseEntity.badRequest()
				.body(new ExceptionResponse(message, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<ExceptionResponse> handleAuthorizationDeniedException(AuthorizationDeniedException e) {
		String message = "로그인이 필요하거나 권한이 없습니다.";

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ExceptionResponse(message, HttpStatus.UNAUTHORIZED));
	}
}
