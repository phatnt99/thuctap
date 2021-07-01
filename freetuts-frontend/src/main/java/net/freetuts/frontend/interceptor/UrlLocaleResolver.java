package net.freetuts.frontend.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class UrlLocaleResolver implements LocaleResolver {

	private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// ==> /SomeContextPath/en/...
		// ==> /SomeContextPath/fr/...
		// ==> /SomeContextPath/WEB-INF/views/...
		String uri = request.getRequestURI();

		String prefixEn = request.getServletContext().getContextPath() + "/en/";
		String prefixFr = request.getServletContext().getContextPath() + "/fr/";
		String prefixVi = request.getServletContext().getContextPath() + "/vi/";

		Locale locale = null;
 
		if (uri.startsWith(prefixEn)) {
			// English
			locale = Locale.ENGLISH;
		} else if (uri.startsWith(prefixVi)) {
			// Vietnamese
			locale = new Locale("vi", "VN");
		}
		if (locale != null) {
			request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		}
		if (locale == null) {
			locale = (Locale) request.getSession().getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
			if (locale == null) {
				locale = Locale.ENGLISH;
			}
		}

		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// Nothing

	}

}
