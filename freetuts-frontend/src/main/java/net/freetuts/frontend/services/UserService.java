package net.freetuts.frontend.services;

import java.util.List;

import net.freetuts.frontend.model.JwtResponse;
import net.freetuts.frontend.model.User;

public interface UserService {

	List<User> getAll();

	User findByUser(String username);
	
	JwtResponse login(User user);

}
