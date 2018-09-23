package com.org.wms.app.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.antMatchers("/login", "/oauth/authorize")
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.permitAll();
	}
	
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//	    String publicKey = null;
//	    try {
//	        publicKey = StreamUtils.copyToString(new ClassPathResource("public_key.txt").getInputStream(), Charset.defaultCharset() );
//	    } catch (final IOException e) {
//	        throw new RuntimeException(e);
//	    }
//	    converter.setVerifierKey(publicKey);
//	    return converter;
//	}
	
}