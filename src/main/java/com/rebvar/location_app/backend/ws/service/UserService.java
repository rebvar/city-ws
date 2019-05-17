package com.rebvar.location_app.backend.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rebvar.location_app.backend.ws.common.dto.UserDTO;

/**
 * @author sehossei
 * User Service. In charge of user operations.
 */
public interface UserService extends UserDetailsService {
	public UserDTO getUserByEmail(String email);
	public UserDTO getUserByUserId(String id);
	public UserDTO createUser(UserDTO inputUserData);
	public UserDTO updateUser(UserDTO userDto);
}
