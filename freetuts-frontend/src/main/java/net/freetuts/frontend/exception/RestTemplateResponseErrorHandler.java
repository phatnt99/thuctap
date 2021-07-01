package net.freetuts.frontend.exception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import net.freetuts.frontend.exception.domain.PostNotFoundException;
import net.freetuts.frontend.exception.domain.UserNotLogin;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		Series httpStatusSeriesCoe = response.getStatusCode().series();

		return ((httpStatusSeriesCoe == HttpStatus.Series.CLIENT_ERROR)
				|| (httpStatusSeriesCoe == HttpStatus.Series.SERVER_ERROR));

	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		Series httpStatusSeriesCoe = response.getStatusCode().series();
		switch (httpStatusSeriesCoe) {
		case SERVER_ERROR:
			handleServerError(response);
			break;
		case CLIENT_ERROR:
			handleClientError(response);
			break;
		default:
			break;
		}

	}

	private void handleClientError(ClientHttpResponse response)
			throws IOException {
		HttpStatus responseCode = response.getStatusCode();

		switch (responseCode) {
		case BAD_REQUEST:
			String result = StreamUtils.copyToString(response.getBody(),
					StandardCharsets.UTF_8);
			throw new UserNotLogin(result);
		case FORBIDDEN:
			result = StreamUtils.copyToString(response.getBody(),
					StandardCharsets.UTF_8);
			throw new UserNotLogin(result);
		case UNAUTHORIZED:
			result = StreamUtils.copyToString(response.getBody(),
					StandardCharsets.UTF_8);
			throw new UserNotLogin(result);
		case NOT_FOUND:
			result = StreamUtils.copyToString(response.getBody(),
					StandardCharsets.UTF_8);
			throw new PostNotFoundException(result);
		default:
			break;
		}

	}

	private void handleServerError(ClientHttpResponse response)
			throws IOException {
		HttpStatus responseCode = response.getStatusCode();

		switch (responseCode) {
		case INTERNAL_SERVER_ERROR:

		default:
			throw new IOException(StreamUtils.copyToString(response.getBody(),
					StandardCharsets.UTF_8));
		}

	}
}
