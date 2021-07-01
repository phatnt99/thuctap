package net.freetuts.frontend.form;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author MaiDat
 *
 */
public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3939173372110846655L;

	private String userName;

	private String password;

	private LocalDate date;

	public UserForm(String userName, String password, LocalDate date) {
		super();

		this.userName = userName;
		this.password = password;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserForm() {
		super();
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", password=" + password + ", date=" + date + "]";
	}

}
