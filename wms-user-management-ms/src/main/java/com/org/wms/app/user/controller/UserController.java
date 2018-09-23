package com.org.wms.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.wms.app.user.collection.RegisteredUser;
import com.org.wms.app.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/details/{username}")
	public RegisteredUser getUserInfo(@PathVariable String username) {
		return userService.findByUsernameIgnoreCase(username);
	}
	
	@GetMapping("/existByName/{username}")
	public Boolean existsByUsernameIgnoCase(@PathVariable String username) {
		return userService.existsByUsernameIgnoreCase(username);
	}
	
	@PostMapping("/create")
	public void createLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		userService.crateUser(username, password);
	}
	
	@PostMapping("/update/profile/basic")
	public void updateUserProfile(@RequestBody RegisteredUser user) {
		userService.updateBasicProfile(user);
	}


}
