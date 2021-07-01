package net.freetuts.backend.repository;

import java.util.UUID;

import net.freetuts.backend.entity.User;

public interface UserRepository extends GenericReposity<User, UUID> {

	User findByUsername(String username);
}
