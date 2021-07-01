package net.freetuts.backend.services.impl;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.freetuts.backend.entity.Role;
import net.freetuts.backend.entity.User;
import net.freetuts.backend.repository.RoleRepository;
import net.freetuts.backend.repository.UserRepository;
import net.freetuts.backend.services.UserService;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl extends GernericServiceImpl<User, UUID>
		implements UserService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Instantiates a new user service impl.
	 *
	 * @param userRepository the user repository
	 */
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Override
	public User findByUsername(String username) {
		logger.info("Find By Username : {}", username);
		User user= userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"User not found with username: " + username);
		}
		return userRepository.findByUsername(username);
	}

	/**
	 * Register.
	 *
	 * @param user the user
	 * @return the user
	 */
	@Override
	public User register(User user) {
		logger.info("Register user : {}", user);

		Set<Role> persistRoles = new HashSet<Role>();
		for (Role role : user.getRoles()) {
			persistRoles.add(roleRepository.findByName(role.getName()));
		}

		user.getRoles().clear();

		for (Role persistRole : persistRoles) {
			user.addRole(persistRole);
		}

		return userRepository.save(user);

	}

}
