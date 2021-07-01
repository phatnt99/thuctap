package net.freetuts.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import net.freetuts.backend.security.UserPrincipal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import net.freetuts.backend.security.SecurityConstant;
import static java.util.Arrays.*;

/**
 * The Class JWTTokenProvider.
 */
@Component
public class JWTTokenProvider {

	/** The secret. */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Generate jwt token.
	 *
	 * @param userPrincipal the user principal
	 * @return the string
	 */
	public String generateJwtToken(UserPrincipal userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create()
				.withIssuer(SecurityConstant.ISSUER)
				// .withAudience(SecurityConstant.AUDIENCE)
				.withArrayClaim(SecurityConstant.AUTHORITIES, claims)
				.withSubject(userPrincipal.getUsername())
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(
						new Date(System.currentTimeMillis()
								+ SecurityConstant.EXPIRATION_TIME))
				.sign(HMAC512(secret.getBytes()));
	}

	/**
	 * Gets the authorities.
	 *
	 * @param token the token
	 * @return the authorities
	 */
	public List<GrantedAuthority> getAuthorities(String token) {
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	/**
	 * Gets the authentication.
	 *
	 * @param username the username
	 * @param authorities the authorities
	 * @param request the request
	 * @return the authentication
	 */
	public Authentication getAuthentication(
			String username,
			List<GrantedAuthority> authorities,
			HttpServletRequest request) {

		UsernamePasswordAuthenticationToken userPasswordAuthToken = new UsernamePasswordAuthenticationToken(
				username, null, authorities);
		userPasswordAuthToken.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));
		return userPasswordAuthToken;
	}

	/**
	 * Checks if is token valid.
	 *
	 * @param username the username
	 * @param token the token
	 * @return true, if is token valid
	 */
	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier       = getJWTVerifier();
		boolean     isTokenExpired = isTokenExpired(verifier, token);

		return StringUtils.isNotEmpty(username)
				&& !isTokenExpired;
	}

	/**
	 * Checks if is token valid.
	 *
	 * @param token the token
	 * @return true, if is token valid
	 */
	public boolean isTokenValid(String token) {
		JWTVerifier verifier       = getJWTVerifier();
		String      username       = verifier.verify(token).getSubject();
		Date        expiration     = verifier.verify(token).getExpiresAt();
		boolean     isTokenExpired = expiration.before(new Date());
		return StringUtils.isNotEmpty(username)
				&& !isTokenExpired;
	}

	/**
	 * Gets the subject.
	 *
	 * @param token the token
	 * @return the subject
	 */
	public String getSubject(String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getSubject();
	}

	/**
	 * Gets the jwt header.
	 *
	 * @param user the user
	 * @return the jwt header
	 */
	public HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_TOKEN_HEADER, generateJwtToken(user));
		return headers;
	}

	/**
	 * Checks if is token expired.
	 *
	 * @param verifier the verifier
	 * @param token the token
	 * @return true, if is token expired
	 */
	public boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date    expiration     = verifier.verify(token).getExpiresAt();
		boolean isTokenExpired = expiration.before(new Date());
		return isTokenExpired;
	}

	/**
	 * Gets the claims from token.
	 *
	 * @param token the token
	 * @return the claims from token
	 */
	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES)
				.asArray(String.class);
	}

	/**
	 * Gets the JWT verifier.
	 *
	 * @return the JWT verifier
	 */
	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = HMAC512(secret);
			verifier = JWT.require(algorithm)
					.withIssuer(SecurityConstant.ISSUER)
					.build();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException(
					SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
		}
		return verifier;
	}

	/**
	 * Gets the claims from user.
	 *
	 * @param user the user
	 * @return the claims from user
	 */
	private String[] getClaimsFromUser(UserPrincipal user) {
		List<String> authorities = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
	}

}
