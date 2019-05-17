package com.rebvar.location_app.backend.ws.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rebvar.location_app.backend.ws.common.dto.UserDTO;
import com.rebvar.location_app.backend.ws.db.entity.UserEntity;
import com.rebvar.location_app.backend.ws.db.repository.UserRepository;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.service.UserService;

import java.util.ArrayList;


import org.modelmapper.ModelMapper;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService{

	/** The user repository. */
	@Autowired 
	UserRepository userRepository;
	
	/** The b crypt encoder. */
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	/** The sutils. */
	@Autowired 
	SecurityUtils sutils;
	
	/**
	 * Creates the user.
	 *
	 * @param inputUserData the input user data
	 * @return the user DTO
	 */
	@Override
	public UserDTO createUser(UserDTO inputUserData) {
				
		//Check if exists
		if (userRepository.findByEmail(inputUserData.getEmail())!=null)
			throw new RuntimeException("Email Address Already Exists. Please Login.");
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity =  mapper.map(inputUserData, UserEntity.class);
		userEntity.setUserId(sutils.getUUID());
		userEntity.setEncryptedPassword(bCryptEncoder.encode(inputUserData.getPassword()));
		
		return mapper.map(userRepository.save(userEntity), UserDTO.class);
	}

	
	/**
	 * Load user by username.
	 *
	 * @param email the email
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity u = userRepository.findByEmail(email);
		if (u == null)
			throw new UsernameNotFoundException("Entered email not found. Please register first.");
		return new User(u.getUserId(), u.getEncryptedPassword(), new ArrayList<>());
	}

	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 */
	@Override
	public UserDTO getUserByEmail(String email) {
		UserEntity u = userRepository.findByEmail(email);
		if (u == null) throw new RuntimeException("User not found");
		ModelMapper mapper = new ModelMapper();
		return mapper.map(u, UserDTO.class);
	}
	
	
	/**
	 * Gets the user by user id.
	 *
	 * @param publicUserId the public user id
	 * @return the user by user id
	 */
	@Override
	public UserDTO getUserByUserId(String publicUserId) {
		UserEntity u = userRepository.findByUserId(publicUserId);
		if (u == null) throw new RuntimeException("User not found");
		ModelMapper mapper = new ModelMapper();
		return mapper.map(u, UserDTO.class);
	}

	
	/**
	 * Update user.
	 *
	 * @param userDto the user dto
	 * @return the user DTO
	 */
	@Override
	public UserDTO updateUser(UserDTO userDto) {
		UserEntity u = userRepository.findByUserId(userDto.getUserId());
		if (u.getEmail().compareTo(userDto.getEmail())!=0)
			throw new RuntimeException("Invalid User Data!"); 
		u.setName(userDto.getName());
		u = userRepository.save(u);
		return new ModelMapper().map(u, UserDTO.class);
	}
}
