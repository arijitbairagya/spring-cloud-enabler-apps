package com.org.wms.app.workorder.documentation;

import java.util.Arrays;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	
	private static Logger LOGGER = LogManager.getLogger(SwaggerConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.org.wms.app.workorder"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));

	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Work Orders Management Rest API", 
				"This API can create and update workorders required for Account Managers and get the list of workorders filtered by creator.", 
				"v1", 
				"WMS project can use this API.", 
				new Contact("Arijit Bairagya", "arijitbairagya@github.io", "arijitbairagya@gmail.com"), 
				"@WMS", "arijitbairagya@github.io", Collections.emptyList());
	}

	@Bean
	SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId(env.getProperty("security.oauth2.client.client-id"))
				.clientSecret(env.getProperty("security.oauth2.client.client-secret"))
				.realm("wo-app-realm")
				.appName("WMS_WORKORDER")
				.scopeSeparator(",")
				.additionalQueryStringParams(null)
				.useBasicAuthenticationWithAccessCodeGrant(true)
				.build();
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(env.getProperty("security.oauth2.client.accessTokenUri"), "oauthtoken"))
				.tokenRequestEndpoint(new TokenRequestEndpoint(env.getProperty("security.oauth2.client.userAuthorizationUri")
						, env.getProperty("security.oauth2.client.client-id")
						, env.getProperty("security.oauth2.client.client-secret")))
				.build();

		LOGGER.debug("Grant Type for swagger - {}",grantType.getType());
		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
				.grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes()))
				.build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { 
				new AuthorizationScope("read", "Read Operation"), 
				new AuthorizationScope("write", "Write Operation")};
//				new AuthorizationScope("wms-wo", "Access WMS-WO API") };
		return scopes;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(
						Arrays.asList(new SecurityReference("spring_oauth", scopes())))
				.forPaths(PathSelectors.any())
				.build();
	}
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}