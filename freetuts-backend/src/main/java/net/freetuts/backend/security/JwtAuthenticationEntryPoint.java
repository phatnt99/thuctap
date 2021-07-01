package net.freetuts.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.freetuts.backend.exception.ExceptionResponse;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException {

		ExceptionResponse httpResponse = new ExceptionResponse();
		httpResponse.setTimestamp(new Date());
		httpResponse.setStatus(FORBIDDEN.value());
		httpResponse.setError(FORBIDDEN.getReasonPhrase());
		httpResponse.setMessage(SecurityConstant.FORBIDDEN_MESSAGE);

		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(FORBIDDEN.value());
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(httpResponse));
		out.flush();
	}
}
