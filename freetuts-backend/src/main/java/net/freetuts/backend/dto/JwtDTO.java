package net.freetuts.backend.dto;

public class JwtDTO {
	private String token;

	private String type = "Bearer ";

	public JwtDTO(String token) {
		super();
		this.token = token;
	}

	public JwtDTO(String token, String type) {
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
