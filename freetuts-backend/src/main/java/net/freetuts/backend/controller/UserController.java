package net.freetuts.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.freetuts.backend.dto.JwtDTO;
import net.freetuts.backend.dto.UserDTO;
import net.freetuts.backend.security.UserPrincipal;
import net.freetuts.backend.services.UserService;
import net.freetuts.backend.utils.JWTTokenProvider;

/**
 * The Class UserController.
 */
//@Api(value = "UserController", tags = "REST APIs related to User Entity!")
@RestController
public class UserController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	/** The user service. */
	@Autowired
	UserService userService;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The jwt token provider. */
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	/**
	 * Login.
	 *
	 * @param userDTO the user DTO
	 * @return the response entity
	 */
	@PostMapping("/admin/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userDTO.getUsername(),
						userDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserPrincipal userPrincipal = (UserPrincipal) authentication
				.getPrincipal();

		boolean isAdminAccount = userPrincipal.isAdminAccount();
		if (!isAdminAccount) {
			throw new AccessDeniedException(null);
		}

		String jwtToken = jwtTokenProvider
				.generateJwtToken(userPrincipal);

		JwtDTO jwtResponseDTO = new JwtDTO(jwtToken);

		return new ResponseEntity<>(jwtResponseDTO, HttpStatus.OK);
	}

}
