package com.rebvar.location_app.backend.ws.location_app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebvar.location_app.backend.ws.service.Impl.UserServiceImpl;
import com.rebvar.location_app.backend.ws.common.dto.UserDTO;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.location_app.controller.UserController;
import com.rebvar.location_app.backend.ws.location_app.model.response.UserDataResponseModel;

/**
 * The Class UserControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

	/** The user controller. */
	@InjectMocks
	UserController userController;
	
	/** The user service. */
	@Mock
	UserServiceImpl userService;
	
	/** The sutils. */
	@Mock
	SecurityUtils sutils;
	
	/** The user dto. */
	UserDTO userDto;
	
	/** The user id. */
	final String USER_ID = "user1";
	
	/** The user token. */
	final String USER_TOKEN = "token"; 
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDTO();
        userDto.setName("User1");
        
        userDto.setEmail("user1@company.com");
        userDto.setUserId(USER_ID);
        userDto.setEncryptedPassword("Pass1");
		
	}

	/**
	 * Test get user.
	 */
	@Test
	final void testGetUser() {
	    when(userService.getUserByUserId(anyString())).thenReturn(userDto);
	    when(sutils.getUserIdFromToken(anyString())).thenReturn(USER_TOKEN);
	    UserDataResponseModel resp = userController.getUser(USER_ID);
	    assertNotNull(resp);
	    assertEquals(USER_ID, resp.getUserId());
	    assertEquals(userDto.getName(), resp.getName());
	}
}
