package net.freetuts.frontend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	private Long id;

	private String username;
	private String password;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;

	public User() {
		super();
	}

	public User(Long id, String username, String password, LocalDate date) {
		super();
		this.id       = id;
		this.username = username;
		this.password = password;
		this.date     = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", date=" + date + "]";
	}

}
