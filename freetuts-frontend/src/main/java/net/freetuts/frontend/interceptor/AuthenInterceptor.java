package net.freetuts.frontend.interceptor;

import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import net.freetuts.frontend.exception.domain.UserNotLogin;
import net.freetuts.frontend.utils.UrlUtil;

public class AuthenInterceptor extends HandlerInterceptorAdapter {

	private String username;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		Cookie tokenCookie = WebUtils.getCookie(request, "Token");
		if (tokenCookie == null) {
			throw new UserNotLogin();
		}

		String                token         = tokenCookie.getValue();
		RequestEntity<String> requestEntity = new RequestEntity<>(
				token,
				HttpMethod.POST,
				new URI(UrlUtil.TOKEN_AUTHEN));

		restTemplate.exchange(requestEntity,
				Boolean.class);

		DecodedJWT jwt = JWT.decode(token);
		username = jwt.getSubject();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("username", username);

	}
}
