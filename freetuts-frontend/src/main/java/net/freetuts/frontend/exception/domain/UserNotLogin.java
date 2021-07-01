package net.freetuts.frontend.exception.domain;

public class UserNotLogin extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946618846143155075L;

	public UserNotLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotLogin(String message) {
		super(message);
	}

	public UserNotLogin(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotLogin(Throwable cause) {
		super(cause);
	}
}
