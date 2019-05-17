package com.rebvar.location_app.backend.ws;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.util.ResourceUtil;

/**
 * The Class LocationWsBackendApplication.
 *
 * @author Rebvar
 */
@SpringBootApplication
public class LocationWsBackendApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
	
		ResourceUtil.LoadApplicationConstants();	
		SpringApplication.run(LocationWsBackendApplication.class, args);
	}
	
	
	/**
	 * B crypt encoder.
	 *
	 * @return the b crypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	
	/**
	 * Spring application context.
	 *
	 * @return the spring application context
	 */
	@Bean 
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	/**
	 * Security utils.
	 *
	 * @return the security utils
	 */
	@Bean
	public SecurityUtils securityUtils()
	{
		return new SecurityUtils();
	}
	
}
