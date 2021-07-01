package net.freetuts.backend.exception.domain;

public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
		super("CategoryNotFoundException ok");
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
