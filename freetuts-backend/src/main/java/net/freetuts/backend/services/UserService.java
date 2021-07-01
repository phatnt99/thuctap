package net.freetuts.backend.services;

import java.util.UUID;

import net.freetuts.backend.entity.User;

public interface UserService extends GenericService<User, UUID> {
	

	User findByUsername(String username);

	User register(User usser);

}
