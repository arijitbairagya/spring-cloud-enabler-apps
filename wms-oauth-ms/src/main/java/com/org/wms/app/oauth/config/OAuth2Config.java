package com.org.wms.app.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.org.wms.app.oauth.service.UserDetailsServiceImpl;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

//	private static Logger LOGGER = LogManager.getLogger(OAuth2Config.class);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Qualifier("authenticationManagerBean")
	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${oauth.tokenTimeout:3600}")
	private int expiration;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	Environment env;
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {

		configurer
		.tokenStore(tokenStore())
		.tokenEnhancer(jwtAccessTokenConverter())
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
	}
	
	@Bean
	public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
		return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new CustomTokenEnhancer();
		converter.setKeyPair(keyStoreFactory().getKeyPair(env.getProperty("encrypt.keystore.alias")));
		return converter;
	}

	@Bean
	public KeyStoreKeyFactory keyStoreFactory() {
		return new KeyStoreKeyFactory(new ClassPathResource("server.jks"), 
				env.getProperty("encrypt.keystore.password").toCharArray());
	}
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
		.inMemory()
		.withClient(env.getProperty("security.oauth2.client.client-id"))
		.secret(passwordEncoder.encode(env.getProperty("security.oauth2.client.client-secret")))
		.accessTokenValiditySeconds(expiration)
		.authorizedGrantTypes("client_credentials", "password", "refresh_token", "authorization_code")
		.scopes("read", "write")
		.autoApprove("read", "write");
	}
	
	@Bean
	public OAuth2RequestFactory requestFactory() {
		CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
		requestFactory.setCheckUserScopes(true);
		return requestFactory;
	}

}
