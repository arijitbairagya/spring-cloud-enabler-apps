package com.org.wms.app.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.wms.app.user.collection.RegisteredUser;

public interface UserRepository extends MongoRepository<RegisteredUser, String>{

	RegisteredUser findByUsernameIgnoreCase(String username);

	boolean existsByUsernameIgnoreCase(String username);
}
