package com.rebvar.location_app.backend.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rebvar.location_app.backend.ws.common.dto.UserDTO;

/**
 * The Interface UserService.
 *
 * @author Rebvar
 * User Service. In charge of user operations.
 */
public interface UserService extends UserDetailsService {
	
	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	public UserDTO getUserByEmail(String email);
	
	/**
	 * Gets the user by user id.
	 *
	 * @param id the id
	 * @return the user by user id
	 */
	public UserDTO getUserByUserId(String id);
	
	/**
	 * Creates the user.
	 *
	 * @param inputUserData the input user data
	 * @return the user DTO
	 */
	public UserDTO createUser(UserDTO inputUserData);
	
	/**
	 * Update user.
	 *
	 * @param userDto the user dto
	 * @return the user DTO
	 */
	public UserDTO updateUser(UserDTO userDto);
}
