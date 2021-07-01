package net.freetuts.frontend.exception.domain;

public class CouponNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195894698809095989L;

	public CouponNotFoundException(String message) {
		super(message);
	}

	public CouponNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponNotFoundException(Throwable cause) {
		super(cause);
	}
}
