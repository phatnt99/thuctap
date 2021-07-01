package net.freetuts.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
 
 
public class UserDTO {

	@NotBlank
	@Size(min = 4, max = 30)
	private String username;

	@NotBlank
	@Size(min = 4, max = 30)
	private String password;

	public UserDTO() {
		super();
	}

	public UserDTO(@NotBlank @Size(min = 4, max = 30) String username,
			@NotBlank @Size(min = 4, max = 30) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
