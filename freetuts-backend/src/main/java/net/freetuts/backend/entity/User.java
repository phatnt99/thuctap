package net.freetuts.backend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

 

@Entity
//@Data
//@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Table(
		uniqueConstraints = @UniqueConstraint(
				columnNames = "username",
				name = "UK_username"))
public class User extends BaseEntity {

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String fullName;

	@ManyToMany(cascade = {
			CascadeType.PERSIST, CascadeType.MERGE
	}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id",
					foreignKey = @ForeignKey(name = "FK_user_role")),
			inverseJoinColumns = @JoinColumn(
					name = "role_id",
					foreignKey = @ForeignKey(name = "FK_role_user")))
	//@EqualsAndHashCode.Exclude
	private Set<Role> roles;
	
	
	
	
	
 
	public User() {
		super();
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	@Override
	public int hashCode() {
		final int prime  = 31;
		int       result = 1;
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + (isLocked ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		User other = (User) obj;
		if (fullName == null) {
			if (other.fullName != null) return false;
		} else if (!fullName.equals(other.fullName)) return false;
		if (isLocked != other.isLocked) return false;
		if (password == null) {
			if (other.password != null) return false;
		} else if (!password.equals(other.password)) return false;
		if (username == null) {
			if (other.username != null) return false;
		} else if (!username.equals(other.username)) return false;
		return true;
	}

	private boolean isLocked;

	public User(String username, String password, String fullName) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	public User(String username, String password, String fullName,
			Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.roles    = roles;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new HashSet<>();
		}
		roles.add(role);
		role.addUser(this);
	}

	public void removeRome(Role role) {
		if (roles != null) {
			roles.remove(role);
			role.removeUser(this);
		}
	}

}
