package net.freetuts.backend.exception.domain;

public class CourseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);
	}

	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CourseNotFoundException(Throwable cause) {
		super(cause);
	}
}
