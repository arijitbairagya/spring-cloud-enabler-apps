package com.org.wms.app.user.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.wms.app.user.collection.RegisteredUser;
import com.org.wms.app.user.collection.UserRole;
import com.org.wms.app.user.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public RegisteredUser findByUsernameIgnoreCase(String username) {
		RegisteredUser user = userRepo.findByUsernameIgnoreCase(username);
		Optional.ofNullable(user).ifPresent(usr -> usr.setPassword(""));
		return user;
	}

	public boolean existsByUsernameIgnoreCase(String username){
		return userRepo.existsByUsernameIgnoreCase(username);
	}
	
	public void saveUser(RegisteredUser user) {
		userRepo.save(user);
	}

	public void crateUser(String username, String password) {
		RegisteredUser user = new RegisteredUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setActive(true);
		Date createDate = new Date();
		user.setCreatedDate(createDate);
		user.setModifiedDate(createDate);
		user.setRole(UserRole.USER.name()); // default role
		userRepo.save(user);
	}

	/**
	 * Update user's basic information line Name and address
	 * 
	 * @param user
	 */
	public void updateBasicProfile(RegisteredUser user) {
		RegisteredUser userDetails = userRepo.findByUsernameIgnoreCase(user.getUsername());
		
		
		userDetails.setFirstName(user.getFirstName());
		userDetails.setLastName(user.getLastName());
		userDetails.setEmail(user.getEmail());
		userDetails.setGender(user.getGender());
		userDetails.setModifiedDate(new Date());

		userRepo.save(userDetails);
	}
}
