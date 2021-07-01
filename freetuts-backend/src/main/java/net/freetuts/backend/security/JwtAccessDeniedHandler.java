package net.freetuts.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.freetuts.backend.exception.ExceptionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException exception) throws IOException {

		ExceptionResponse httpResponse = new ExceptionResponse();
		httpResponse.setTimestamp(new Date());
		httpResponse.setStatus(UNAUTHORIZED.value());
		httpResponse.setError(UNAUTHORIZED.getReasonPhrase());
		httpResponse.setMessage(SecurityConstant.ACCESS_DENIED_MESSAGE);
		httpResponse.setPath(request.getRequestURL().toString());

		response.setContentType(APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setStatus(UNAUTHORIZED.value());

		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(httpResponse));
		out.flush();
	}
}
