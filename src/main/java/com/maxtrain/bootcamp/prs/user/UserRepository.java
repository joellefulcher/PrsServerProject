package com.maxtrain.bootcamp.prs.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> { // Integer is the object type, not an integer
	User findByUsernameAndPassword(String username, String password);
}
