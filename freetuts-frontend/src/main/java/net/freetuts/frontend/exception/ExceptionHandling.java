package net.freetuts.frontend.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.freetuts.frontend.exception.domain.PostNotFoundException;
import net.freetuts.frontend.exception.domain.UserNotLogin;
 
@ControllerAdvice
public class ExceptionHandling {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionHandling.class);

	// @Value("{account.lock}")
	// private String accountLock;
	//
	// @Value("{url.wrong}")
	// private String wrongInput;

//	private static final String ACCOUNT_LOCKED            = "Your account has been locked. Please contact administration";
//	private static final String METHOD_IS_NOT_ALLOWED     = "This request method is not allowed on this endpoint. Please send a '%s' request";
//	private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
//	private static final String INCORRECT_CREDENTIALS     = "Username / password incorrect. Please try again";
//	private static final String ACCOUNT_DISABLED          = "Your account has been disabled. If this is an error, please contact administration";
//	private static final String ERROR_PROCESSING_FILE     = "Error occurred while processing file";
//	private static final String NOT_ENOUGH_PERMISSION     = "You do not have enough permission";

 
	@ExceptionHandler
	public String userNotLogin(UserNotLogin exception,
			HttpServletRequest request,HttpServletResponse respone) {
		LOGGER.error(exception.getMessage());

	return "redirect:/admin/login";
	}
	
	@ExceptionHandler
	public String notFoundEntity(PostNotFoundException exception,
			HttpServletRequest request,HttpServletResponse respone) {
		LOGGER.error(exception.getMessage());

	return "redirect:/";
	}

	@ExceptionHandler
	public String internalServerErrorException(
			Exception exception,
			HttpServletRequest request) {
 
		return "error500";
 
	}

 
}
