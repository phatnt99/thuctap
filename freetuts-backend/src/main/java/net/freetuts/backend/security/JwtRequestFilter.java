package net.freetuts.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.web.servlet.HandlerExceptionResolver;

import net.freetuts.backend.utils.JWTTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.freetuts.backend.security.SecurityConstant.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			// Get Token Header
			final String authorizationHeader = request
					.getHeader(HttpHeaders.AUTHORIZATION);

			if (authorizationHeader == null
					|| !authorizationHeader.startsWith(TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			// JWT Token is in the form "Bearer token". Remove Bearer word and
			// get
			// only the Token
			String token = authorizationHeader
					.substring(TOKEN_PREFIX.length());

			String username = jwtTokenProvider.getSubject(token);
			if (jwtTokenProvider.isTokenValid(username, token)
					&& SecurityContextHolder.getContext()
							.getAuthentication() == null) {

				List<GrantedAuthority> authorities = jwtTokenProvider
						.getAuthorities(token);

				Authentication authentication = jwtTokenProvider
						.getAuthentication(username, authorities, request);

				SecurityContextHolder.getContext()
						.setAuthentication(authentication);
			} else {
				SecurityContextHolder.clearContext();
			}
 
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			resolver.resolveException(request, response, null, e);
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String path = request.getRequestURI();
		return !path.contains("/admin");

	}

}
