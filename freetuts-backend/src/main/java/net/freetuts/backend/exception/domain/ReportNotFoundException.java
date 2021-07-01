package net.freetuts.backend.exception.domain;

public class ReportNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195894698809095989L;

	public ReportNotFoundException(String message) {
		super(message);
	}

	public ReportNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportNotFoundException(Throwable cause) {
		super(cause);
	}
}
