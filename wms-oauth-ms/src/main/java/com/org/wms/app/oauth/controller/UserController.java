package com.org.wms.app.oauth.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@RestController
public class UserController {

	private static Logger LOGGER = LogManager.getLogger(UserController.class);
	
	@GetMapping("/user")
	public Principal getUser(Principal principal) {
		LOGGER.debug("Loggerin user: ", principal.getName());
		return principal;
	}
}
