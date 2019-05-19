package com.rebvar.location_app.backend.ws.location_app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rebvar.location_app.backend.ws.AppConstants;

/**
 * The Class HomeController.
 */
@RestController

public class HomeController {
	
	/**
	 * Dummy request.
	 *
	 * @param Auth_Token the auth token
	 * @return a string prompting invalid request. Used to adjust security with the application.properties parameter to enable or disable security. The method is exposed when security is enabled.
	 */
	@RequestMapping(path = "/invalid", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
	public String invalidRequest(@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{	
		return "Invalid Request.";
	}	
}
