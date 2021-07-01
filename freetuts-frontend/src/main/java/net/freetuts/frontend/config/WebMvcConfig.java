package net.freetuts.frontend.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import net.freetuts.frontend.exception.RestTemplateResponseErrorHandler;
import net.freetuts.frontend.interceptor.AuthenInterceptor;
import net.freetuts.frontend.interceptor.MenuInterceptor;
import net.freetuts.frontend.interceptor.RestTemplateInterceptor;
import net.freetuts.frontend.interceptor.SidebarInterceptor;
import net.freetuts.frontend.interceptor.UrlLocaleInterceptor;
import net.freetuts.frontend.interceptor.UrlLocaleResolver;

/**
 * Spring Web MVC Config
 * 
 * @author DatMV1
 * 
 * @date 13/05/2021
 */
@Configuration
@EnableWebMvc
@ComponentScan({
		"net.freetuts.frontend"
})
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * Default Character Encoding : UTF - 8
	 */
	private static final String CHARSET = "UTF-8";

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String publicFilesDir = String.format("file:%s/userfiles/",
				System.getProperty("user.dir"));
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
		registry.addResourceHandler("/userfiles/**")
				.addResourceLocations(publicFilesDir);
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(CHARSET);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setCharacterEncoding(CHARSET);
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setUseCodeAsDefaultMessage(true);
		messageResource.setDefaultEncoding(CHARSET);
		messageResource.setCacheSeconds(5);

		return messageResource;
	}

	// To solver URL like:
	// http://localhost:8080/en/login
	// http://localhost:8080/vi/login
	// http://localhost:8080/fr/login
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		return new UrlLocaleResolver();
	}

	// @Bean
	// public RestTemplate restTemplate() {
	// final RestTemplate restTemplate = new RestTemplate();
	// List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	// MappingJackson2HttpMessageConverter converter = new
	// MappingJackson2HttpMessageConverter();
	// converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
	// messageConverters.add(converter);
	// restTemplate.setMessageConverters(messageConverters);
	// return restTemplate;
	//
	// }

	@Bean
	public SidebarInterceptor sidebarInterceptor() {
		return new SidebarInterceptor();
	}

	@Bean
	public MenuInterceptor menuInterceptor() {
		return new MenuInterceptor();
	}
	
	@Bean
	public AuthenInterceptor authenInterceptor() {
		return new AuthenInterceptor();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();
		registry.addInterceptor(localeInterceptor).addPathPatterns("/en/*",
				"/fr/*", "/vi/*");

		registry.addInterceptor(sidebarInterceptor())
		.excludePathPatterns("/admin/**", "/resources/**", "/ajax/**");
		
		
		registry.addInterceptor(authenInterceptor())
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/admin/login", "/admin/logout","/resources/**");

		
		registry.addInterceptor(menuInterceptor())
				.excludePathPatterns("/admin/**", "/resources/**", "/ajax/**");

	}

	@Bean
	public RestTemplate restTemplate(
			RestTemplateInterceptor restTemplateInterceptor) {
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
				new HttpComponentsClientHttpRequestFactory());

		RestTemplate restTemplate = new RestTemplate(factory);

		List<HttpMessageConverter<?>>       messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter         = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(
				Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		restTemplate.setInterceptors(Collections
				.singletonList(restTemplateInterceptor));

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return restTemplate;

	}

}
