package net.freetuts.frontend.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ckfinder.connector.ConnectorServlet;


@Configuration
public class CKFinderServletConfiguration {

	@Bean
	public ServletRegistration config(ServletContext servletContext) throws ServletException{
		ServletRegistration.Dynamic dispatcher = servletContext
				.addServlet("ConnectorServlet", new ConnectorServlet());
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/resources/public/plugin/ckfinder/core/connector/java/connector.java");
		dispatcher.setInitParameter("XMLConfig", "/WEB-INF/config.xml");
		
		return dispatcher;

	}

}
