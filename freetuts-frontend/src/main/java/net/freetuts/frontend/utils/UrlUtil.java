package net.freetuts.frontend.utils;

import java.util.ArrayList;
import java.util.List;

import net.freetuts.frontend.model.PageAndSort;

public class UrlUtil {

	public static final String BACKEND_PORT = "1234";

	public static final String BACKEND_CONTEXT_PATH = "/freetuts/api";

	public static final String BACKEND_URL = "http://localhost:" + BACKEND_PORT
			+ BACKEND_CONTEXT_PATH;
	
	public static final String ADMIN_REST = BACKEND_URL + "/admin";
	
	/**
	 * http://localhost:1234/freetuts/api/user
	 */
	public static final String USER_REST = BACKEND_URL + "/user";

	public static final String POST_REST = BACKEND_URL + "/posts";
	
	public static final String POST_ADMIN_REST = ADMIN_REST + "/posts";

	public static final String CATE_REST = BACKEND_URL + "/categories";
	
	public static final String CATE_ADMIN_REST = ADMIN_REST + "/categories";
	
	public static final String TUT_REST = BACKEND_URL + "/tutorials";
	
	public static final String TUT_ADMIN_REST = ADMIN_REST + "/posts";
	
	public static final String DISPLAY_REST = BACKEND_URL + "/display";

	/**
	 * @author ThuyDTT14
	 */

	public static final String COURSE_REST = BACKEND_URL + "/courses";
	public static final String COURSE_ADMIN_REST = ADMIN_REST + "/courses";
	public static final String COMBO_REST  = BACKEND_URL + "/courses/combo";
	public static final String INVOICE_REST  = BACKEND_URL + "/invoices";

	
	public static final String COUPON_REST = BACKEND_URL + "/coupon";
	
	public static final String REPORT_REST = BACKEND_URL + "/report";
	public static final String REPORT_ADMIN_REST = BACKEND_URL + "/admin/report";
	
	public static final String COUPON_ADMIN_REST = ADMIN_REST + "/coupon";
	
	public static final String LOGIN_ADMIN = BACKEND_URL + "/admin/login";
	
	public static final String TOKEN_AUTHEN = BACKEND_URL + "/token/authentication";
	public static String buildPageAndSort(String host,
			PageAndSort pageAndSort) {

		UrlBuilder url = pageAndSort != null ? new UrlBuilder(host).addPaginateAndSort(pageAndSort) : new UrlBuilder(host);

		return url.get();
	}
}

class UrlBuilder {

	StringBuilder    host;
	List<QueryParam> params;

	UrlBuilder(String host) {
		this.host = new StringBuilder();
		this.host.append(host);
		this.params = new ArrayList<QueryParam>();
	}

	UrlBuilder addParam(String key, String value) {
		if (params.size() > 0) {
			this.host.append("&" + key + "=" + value);
		} else {
			this.host.append("?" + key + "=" + value);
		}
		this.params.add(new QueryParam(key, value));

		return this;
	}

	UrlBuilder addPaginateAndSort(PageAndSort obj) {
		// page and size is provided by default
		this.addParam("page", obj.getPage().toString());
		this.addParam("size", obj.getSize().toString());
		// TODO check for sort

		return this;
	}

	String get() {

		return this.host.toString();
	}

	class QueryParam {

		String key;
		String value;

		QueryParam(String key, String value) {
			this.key   = key;
			this.value = value;
		}
	}
}
