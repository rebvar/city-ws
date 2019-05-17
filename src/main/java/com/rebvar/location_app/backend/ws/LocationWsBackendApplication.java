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

@SpringBootApplication
public class LocationWsBackendApplication {

	public static void main(String[] args) {
	
		ResourceUtil.LoadApplicationConstants();	
		SpringApplication.run(LocationWsBackendApplication.class, args);
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	
	@Bean 
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	@Bean
	public SecurityUtils securityUtils()
	{
		return new SecurityUtils();
	}
	
}
