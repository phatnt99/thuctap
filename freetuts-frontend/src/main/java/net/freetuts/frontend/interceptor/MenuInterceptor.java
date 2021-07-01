package net.freetuts.frontend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.freetuts.frontend.services.DisplayService;

public class MenuInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private DisplayService displayService;
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println(request.getRequestURI());

		modelAndView.addObject("menu", displayService.getMenu());

	}
	
}
