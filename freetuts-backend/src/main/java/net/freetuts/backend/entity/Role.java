package net.freetuts.backend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
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
				columnNames = "name",
				name = "UK_name"))
public class Role extends BaseEntity {

	private String name;

//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
	
	

	@Override
	public String toString() {
		return "name=" + name;
	}

	@Override
	public int hashCode() {
		final int prime  = 31;
		int       result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	public Role() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public void addUser(User user) {
		if (this.users == null) {
			this.users = new HashSet<>();
		}
		this.users.add(user);
	}

	public void removeUser(User user) {
		if (this.users != null) {
			this.users.remove(user);
		}
	}

}
