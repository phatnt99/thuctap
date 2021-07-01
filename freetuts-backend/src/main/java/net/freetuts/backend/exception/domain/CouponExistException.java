package net.freetuts.backend.exception.domain;

public class CouponExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7722192899671900322L;

	public CouponExistException(String message) {
		super(message);
	}

	public CouponExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponExistException(Throwable cause) {
		super(cause);
	}
}
