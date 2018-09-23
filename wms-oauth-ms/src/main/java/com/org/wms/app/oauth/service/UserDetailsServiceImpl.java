package com.org.wms.app.oauth.service;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.wms.app.oauth.repository.UserRepository;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private static Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOGGER.debug("User name to get the details... {}", username);
		String lowercaseLogin = username.toLowerCase();

//		User userFromDatabase;
//		if(lowercaseLogin.contains("@")) {
//			userFromDatabase = userRepository.findByEmail(lowercaseLogin);
//		} else {
//			userFromDatabase = userRepository.findByUsernameCaseInsensitive(lowercaseLogin);
//		}

		com.org.wms.app.oauth.domain.user.RegisteredUser userFromDatabase = userRepository.findByUsernameIgnoreCase(username);
		if (userFromDatabase == null) {
			LOGGER.debug("User not found!");
			throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
		} 
//		else if (!userFromDatabase.isActivated()) {
//			throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated");
//		}

//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//		for (Authority authority : userFromDatabase.getAuthorities()) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
//			grantedAuthorities.add(grantedAuthority);
//		}

		return new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername()
				, passwordEncoder.encode(userFromDatabase.getPassword())
				, Arrays.asList(new SimpleGrantedAuthority(userFromDatabase.getRole())));
	}
}