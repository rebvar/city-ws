package com.rebvar.location_app.backend.ws.db.repository;
	
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebvar.location_app.backend.ws.db.entity.UserEntity;
import com.rebvar.location_app.backend.ws.db.repository.UserRepository;

/**
 * The Class UserRepositoryTests.
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTests {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	/** The data created. */
	static boolean dataCreated = false;
	

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		if(!dataCreated) createRecrods();
	}
	
	/**
	 * Test find user by name.
	 */
	@Test 
	final void testFindUserByName()
	{
		String name="User1";
		List<UserEntity> users = userRepository.findAllByName(name);
		assertNotNull(users);
		assertTrue(users.size() == 1);
		UserEntity user = users.get(0);
		assertTrue(user.getName().equals(name));
	}
	
	/**
	 * Test find users by pattern.
	 */
	@Test 
	final void testFindUsersByPattern()
	{
		String pattern="ser";
		List<UserEntity> users = userRepository.findUsersByPattern(pattern);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue(
				user.getName().contains(pattern)
				);
	}
		
	
	/**
	 * Test find user entity by user id.
	 */
	@Test 
	final void testFindUserEntityByUserId()
	{
		String userId = "User1RandomId1";
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId));
	}
	
	/**
	 * Creates the recrods.
	 */
	private void createRecrods()
	{
		
		userRepository.deleteAll();
	     UserEntity user1 = new UserEntity();
	     user1.setName("User1");
	     user1.setUserId("User1RandomId1");
	     user1.setEncryptedPassword("User1Password");
	     user1.setEmail("user1@company.com");	     
	     
	     userRepository.save(user1);
	     
	     UserEntity user2 = new UserEntity();
	     user2.setName("User2");
	     user2.setUserId("User2RandomId2");
	     user2.setEncryptedPassword("User2Password");
	     user2.setEmail("user2@company.com");
	     
	     userRepository.save(user2);
	     
	     dataCreated = true;
	}
}


