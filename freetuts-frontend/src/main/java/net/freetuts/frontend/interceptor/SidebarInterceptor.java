package net.freetuts.frontend.interceptor;

import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import net.freetuts.frontend.exception.domain.UserNotLogin;
import net.freetuts.frontend.model.Coupon;
import net.freetuts.frontend.model.Course;
import net.freetuts.frontend.model.CourseSideBar;
import net.freetuts.frontend.model.PageAndSort;
import net.freetuts.frontend.model.Post;
import net.freetuts.frontend.services.CouponService;
import net.freetuts.frontend.services.CourseService;
import net.freetuts.frontend.services.PostService;
import net.freetuts.frontend.utils.RestPage;
import net.freetuts.frontend.utils.UrlUtil;

public class SidebarInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CouponService couponService;

	@Autowired
	private CourseService courseService;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// If it is ajax request response header will have x-requested-with

		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with")
						.equalsIgnoreCase("XMLHttpRequest")) {
			return;
		}

		modelAndView.addObject("coupons", getTop3Coupon());

		modelAndView.addObject("courses", getTop5Courses());

	}

	private RestPage<Coupon> getTop3Coupon() {
		PageAndSort pageAndSort = new PageAndSort();
		pageAndSort.setPage(0);
		pageAndSort.setSize(3);
		return couponService.getAll(pageAndSort);

	}

	private List<CourseSideBar> getTop5Courses() {

		return courseService.getTop5ByTypeOrderByUpdatedAtDesc(0);

	}
}
