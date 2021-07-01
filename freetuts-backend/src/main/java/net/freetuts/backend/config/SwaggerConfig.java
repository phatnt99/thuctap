package net.freetuts.backend.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import net.freetuts.backend.security.SecurityConstant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// URL Swagger UI = http://localhost:1234/freetuts/api/swagger-ui.html

	// @Bean
	// public Docket api() {
	// return new Docket(DocumentationType.SWAGGER_2)
	// .groupName("public-api")
	// .select()
	// .apis(RequestHandlerSelectors
	// .basePackage("net.freetuts.backend"))
	// .paths(PathSelectors.regex("/.*"))
	// .build().apiInfo(apiEndPointsInfo());
	// }
	//
	// private ApiInfo apiEndPointsInfo() {
	// return new ApiInfoBuilder()
	// .title("Freetuts REST API")
	// .description("Freetuts Management REST API")
	// .contact(new Contact("ok",
	// "http://localhost:8080/freetuts-frontend",
	// "ok@gmail.com"))
	// .license("Apache 2.0")
	// .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	// .version("1.0.0")
	// .build();
	// }

	// URL Swagger UI = http://localhost:1234/freetuts/api/swagger-ui.html
	public static final String DEFAULT_INCLUDE_PATTERN = "/admin/.*";
	private final Logger       log                     = LoggerFactory
			.getLogger(SwaggerConfig.class);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("net.freetuts.backend"))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(apiEndPointsInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder()
				.title("Freetuts REST API")
				.description("Freetuts Management REST API")
				.contact(new Contact("ok",
						"http://localhost:8080/freetuts-frontend",
						"abcvxyz@gmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey(SecurityConstant.JWT_TOKEN_HEADER,
				HttpHeaders.AUTHORIZATION, "header");
	}

	private SecurityContext securityContext() {

		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope   authorizationScope  = new AuthorizationScope(
				"global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(
				SecurityConstant.JWT_TOKEN_HEADER, authorizationScopes));
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder
				.builder()
				.operationsSorter(OperationsSorter.ALPHA)
				.build();
	}

}
