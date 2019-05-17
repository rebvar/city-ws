package com.rebvar.location_app.backend.ws.location_app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rebvar.location_app.backend.ws.AppConstants;

@RestController

public class HomeController {
	@RequestMapping(path = "/invalid", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
	public String invalidRequest(@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{	
		return "Invalid Request.";
	}	
}
