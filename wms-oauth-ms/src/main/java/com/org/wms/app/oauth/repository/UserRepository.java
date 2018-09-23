package com.org.wms.app.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.wms.app.oauth.domain.user.RegisteredUser;

/**
 * Access to the user data. 
 *
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
public interface UserRepository extends MongoRepository<RegisteredUser, String>{

	RegisteredUser findByUsernameIgnoreCase(String username);
}
