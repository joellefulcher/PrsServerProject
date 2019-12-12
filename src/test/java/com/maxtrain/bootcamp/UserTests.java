package com.maxtrain.bootcamp;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.maxtrain.bootcamp.prs.user.User;
import com.maxtrain.bootcamp.prs.user.UserRepository;

@RunWith(SpringRunner.class) //run, run config, JUnit4
@SpringBootTest
public class UserTests {
	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testFindAll() {
		Iterable<User> users = userRepo.findAll();
		assertNotNull(users);
		// test that the list returned is greater than 0
		assertTrue(((Collection<User>) users).size()>0);
	}
	
	@Test
	public void testUserAdd() {
		// create a new user
		User u = new User("tuser", "pwd", "test", "user", "513-111-2222","test@test.com", true,true, true);
		assertNotNull(userRepo.save(u));
	}

}
