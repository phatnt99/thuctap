package net.freetuts.frontend.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class RestTemplateInterceptor
		implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution)
			throws IOException {

		if (request.getURI().getPath().contains("/admin")) {
			String token = getTokenFromCookie();
			if (token != null) {
				request.getHeaders().set(HttpHeaders.AUTHORIZATION, token);
			}
		}

		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);

		return response;

	}

	private void traceRequest(HttpRequest request, byte[] body)
			throws IOException {
		System.out.println(
				"===========================request begin================================================");
		System.out.println("URI         : " + request.getURI());
		System.out.println("Method      : " + request.getMethod());
		System.out.println("Headers     : " + request.getHeaders());

		System.out.println(
				"Request body: " + new String(body, StandardCharsets.UTF_8));
		System.out.println(
				"==========================request end================================================");
	}

	private HttpServletRequest getCurrentHttpRequest() {
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) requestAttributes)
					.getRequest();

		}
		return null;
	}

	private String getTokenFromCookie() {

		HttpServletRequest httpServletRequest = getCurrentHttpRequest();
		if (httpServletRequest != null) {
			Cookie tokenCookie = WebUtils.getCookie(httpServletRequest,
					"Token");
			if (tokenCookie != null) {
				return "Bearer " + tokenCookie.getValue();
			}

		}

		return null;
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		StringBuilder  inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader     = new BufferedReader(
				new InputStreamReader(response.getBody(),
						StandardCharsets.UTF_8));
		String         line               = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			inputStringBuilder.append('\n');
			line = bufferedReader.readLine();
		}
		System.out.println(
				"============================response begin==========================================");
		System.out.println("Status code  : " + response.getStatusCode());
		System.out.println("Status text  : " + response.getStatusCode().getReasonPhrase());
		System.out.println("Headers      : " + response.getHeaders());
		System.out.println("Response body: " + inputStringBuilder.toString());
		System.out.println(
				"=======================response end=================================================");
	}

}