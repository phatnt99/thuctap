package net.freetuts.frontend.exception.domain;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException() {
		super("PostNotFoundException ok");
		// TODO Auto-generated constructor stub
	}

	public PostNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PostNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PostNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
