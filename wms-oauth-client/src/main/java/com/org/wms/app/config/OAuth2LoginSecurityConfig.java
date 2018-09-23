//package com.org.wms.app.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
//import org.springframework.util.CollectionUtils;
//import sample.data.UserAuthority;
//import sample.data.UserAuthorityRepository;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
///**
// * @author Arijit Bairagya
// */
//@EnableWebSecurity
//public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserAuthorityRepository userAuthorityRepository;
//
//	// @formatter:off
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.mvcMatchers("/create-message").hasAuthority("CREATE_MESSAGE")
//				.mvcMatchers("/read-message").hasAuthority("READ_MESSAGE")
//				.mvcMatchers("/update-message").hasAuthority("UPDATE_MESSAGE")
//				.mvcMatchers("/delete-message").hasAuthority("DELETE_MESSAGE")
//				.mvcMatchers("/manage-message").hasAuthority("MANAGE_MESSAGE")
//				.anyRequest().authenticated()
//				.and()
//			.oauth2Login()
//				.userInfoEndpoint()
//					.userAuthoritiesMapper(this.authoritiesMapper());
//	}
//	// @formatter:on
//
//	private GrantedAuthoritiesMapper authoritiesMapper() {
//		return (authorities) -> {
//			String emailAttrName = "email";
//			String email = authorities.stream()
//					.filter(OAuth2UserAuthority.class::isInstance)
//					.map(OAuth2UserAuthority.class::cast)
//					.filter(userAuthority -> userAuthority.getAttributes().containsKey(emailAttrName))
//					.map(userAuthority -> userAuthority.getAttributes().get(emailAttrName).toString())
//					.findFirst()
//					.orElse(null);
//			if (email == null) {
//				return authorities;		// Return the 'unmapped' authorities
//			}
//			Collection<UserAuthority> userAuthorities = this.userAuthorityRepository.findByUserId(email);
//			if (CollectionUtils.isEmpty(userAuthorities)) {
//				return authorities;		// Return the 'unmapped' authorities
//			}
//			return userAuthorities.stream()
//					.map(UserAuthority::getAuthority)
//					.map(SimpleGrantedAuthority::new)
//					.collect(Collectors.toCollection(ArrayList::new));
//		};
//	}
//}