package com.rebvar.location_app.backend.ws.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebvar.location_app.backend.ws.db.entity.UserEntity;
import com.rebvar.location_app.backend.ws.db.repository.UserRepository;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.common.dto.UserDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
	
	@Mock
	BCryptPasswordEncoder bCryptEncoder;

	@Mock
	SecurityUtils sutils;

	String userId = "user1";
	String encryptedPassword = "pass1";

	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setName("User1");
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setEmail("user1@company.com");
	}

	@Test
	final void testGetUser() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDTO userDto = userService.getUserByEmail("user1@company.com");

		assertNotNull(userDto);
		assertEquals("User1", userDto.getName());

	}

	@Test
	final void testGetUser_Exception() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(RuntimeException.class,

				() -> {
					userService.getUserByEmail("user1@company.com");
				}

		);
	}

	@Test
	final void testCreateUser_Exception() {
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDTO userDto = new UserDTO();
		userDto.setName("User1");
		userDto.setPassword("123");
		userDto.setEmail("user1@company.com");
		userDto.setUserId("AnotherId");
		assertThrows(RuntimeException.class,

				() -> {
					userService.createUser(userDto);
				});
	}

	@Test
	final void testCreateUser() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(bCryptEncoder.encode(anyString())).thenReturn(encryptedPassword);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		when(sutils.getUUID()).thenReturn(userId);

		UserDTO userDto = new UserDTO();
		userDto.setName("User1");
		userDto.setPassword("123");
		userDto.setEmail("user1@company.com");
		userDto.setEncryptedPassword(encryptedPassword);
		
		UserDTO storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getName(), storedUserDetails.getName());
		assertNotNull(storedUserDetails.getUserId());
		verify(userRepository, times(1)).save(any(UserEntity.class));

	}
}
