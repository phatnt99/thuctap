package net.freetuts.frontend.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	private String type = "Bearer";

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public JwtResponse(String token, String type) {
		super();
		this.token = token;
		this.type  = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
