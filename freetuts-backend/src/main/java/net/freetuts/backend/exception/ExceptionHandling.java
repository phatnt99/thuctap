package net.freetuts.backend.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import net.freetuts.backend.exception.domain.CategoryNotFoundException;
import net.freetuts.backend.exception.domain.CourseNotFoundException;
import net.freetuts.backend.exception.domain.PostNotFoundException;

@RestControllerAdvice
public class ExceptionHandling {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionHandling.class);

	// May cai string nay viet trong file i18/exception.properties ma lam biemg
	// viet qua
	// @Value("{account.lock}")
	// private String accountLock;
	//
	// @Value("{url.wrong}")
	// private String wrongInput;

	private static final String ACCOUNT_LOCKED            = "Your account has been locked. Please contact administration";
	private static final String METHOD_IS_NOT_ALLOWED     = "This request method is not allowed on this endpoint. Please send a '%s' request";
	private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
	private static final String INCORRECT_CREDENTIALS     = "Username / password incorrect. Please try again";
	private static final String ACCOUNT_DISABLED          = "Your account has been disabled. If this is an error, please contact administration";
	private static final String ERROR_PROCESSING_FILE     = "Error occurred while processing file";
	private static final String NOT_ENOUGH_PERMISSION     = "You do not have enough permission";

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<Object> accountDisabledException(
			HttpServletRequest request) {
		return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED, request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> badCredentialsException(
			HttpServletRequest request) {
		return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS, request);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> accessDeniedException(
			AccessDeniedException exception,
			HttpServletRequest request) {
		return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION, request);
	}

	@ExceptionHandler(LockedException.class)
	public ResponseEntity<Object> lockedException(
			HttpServletRequest request) {
		return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED, request);
	}

	// @ExceptionHandler
	// public ResponseEntity<Object> tokenExpiredException(
	// TokenExpiredException exception,
	// HttpServletRequest request) {
	// return createHttpResponse(UNAUTHORIZED, exception.getMessage(),
	// request);
	// }

	// @ExceptionHandler
	// public ResponseEntity<Object> couponNotFoundException(
	// CouponNotFoundException exception,
	// HttpServletRequest request) {
	// return createHttpResponse(NOT_FOUND, exception.getMessage(),
	// request);
	// }

	// @ExceptionHandler
	// public ResponseEntity<Object> couponExistException(
	// CouponExistException exception,
	// HttpServletRequest request) {
	// LOGGER.error(exception.getMessage());
	// return createHttpResponse(CONFLICT, exception.getMessage(), request);
	// }

	@ExceptionHandler
	public ResponseEntity<Object> noHandlerFoundException(
			NoHandlerFoundException e,
			HttpServletRequest request) {
		return createHttpResponse(BAD_REQUEST,
				"There is no mapping for this URL", request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> methodNotSupportedException(
			HttpRequestMethodNotSupportedException exception,
			HttpServletRequest request) {
		HttpMethod supportedMethod = Objects
				.requireNonNull(exception.getSupportedHttpMethods()).iterator()
				.next();
		return createHttpResponse(METHOD_NOT_ALLOWED,
				String.format(METHOD_IS_NOT_ALLOWED, supportedMethod), request);
	}

	// @ExceptionHandler
	// public ResponseEntity<Object> notAnImageFileException(
	// NotAnImageFileException exception,
	// HttpServletRequest request) {
	// LOGGER.error(exception.getMessage());
	// return createHttpResponse(BAD_REQUEST, exception.getMessage(), request);
	// }

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Object> categoryNotFoundException(
			CategoryNotFoundException exception, HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(NOT_FOUND, exception.getMessage(), request);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> postNotFoundException(
			PostNotFoundException exception, HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(NOT_FOUND, exception.getMessage(), request);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> courseNotFoundException(
			CourseNotFoundException exception, HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(NOT_FOUND, exception.getMessage(), request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> notFoundException(
			NoResultException exception,
			HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(NOT_FOUND, exception.getMessage(), request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> iOException(IOException exception,
			HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE,
				request);
	}

	@ExceptionHandler
	public ResponseEntity<Object> internalServerErrorException(
			Exception exception,
			HttpServletRequest request) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR,
				INTERNAL_SERVER_ERROR_MSG, request);
	}

	private ResponseEntity<Object> createHttpResponse(
			HttpStatus httpStatus, String message, HttpServletRequest request) {

		ExceptionResponse httpResponse = new ExceptionResponse();

		httpResponse.setTimestamp(new Date());
		httpResponse.setStatus(httpStatus.value());
		httpResponse.setError(httpStatus.getReasonPhrase());
		httpResponse.setMessage(message);
		httpResponse.setPath(request.getRequestURL().toString());

		return new ResponseEntity<>(
				httpResponse,
				httpStatus);
	}

}