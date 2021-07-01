package net.freetuts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.utils.JWTTokenProvider;

/**
 * The Class TokenController.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

	/** The jwt token provider. */
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	/**
	 * Authen token.
	 *
	 * @param token the token
	 * @return the response entity
	 */
	@PostMapping(value = "/authentication")
	public ResponseEntity<?> authenToken( @RequestBody String token) {	 
		token = token.replace("\"", "");
		boolean isTokenValid = jwtTokenProvider.isTokenValid(token);
		return new ResponseEntity<>(isTokenValid, HttpStatus.OK);

	}
}
