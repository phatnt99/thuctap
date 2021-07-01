package net.freetuts.frontend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.freetuts.frontend.model.JwtResponse;
import net.freetuts.frontend.model.User;
import net.freetuts.frontend.services.UserService;
import net.freetuts.frontend.utils.UrlUtil;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The rest template. */
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find by user.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Override
	public User findByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Login.
	 *
	 * @param user the user
	 * @return the jwt response
	 */
	@Override
	public JwtResponse login(User user) {
 
		String url = UrlUtil.LOGIN_ADMIN;
 
		
		ResponseEntity<JwtResponse> responseJwt = restTemplate
				.postForEntity(
						url,
						 user,JwtResponse.class
					);
		 
		 
		return responseJwt.getBody();
	}

}
