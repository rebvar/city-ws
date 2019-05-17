package com.rebvar.location_app.backend.ws.security;

import java.util.UUID;

import com.rebvar.location_app.backend.ws.AppConstants;

import io.jsonwebtoken.Jwts;

/**
 * The Class SecurityUtils.
 */
public class SecurityUtils {
	
	/**
	 * Gets the user id from token.
	 *
	 * @param token the token
	 * @return the user id from token
	 */
	public String getUserIdFromToken(String token)
	{
		if(token == null)
			return "";
		
		token = token.replace(AppConstants.TOKEN_PREFIX, "");
        String user;
		try
		{
			user = Jwts.parser()
                .setSigningKey(AppConstants.TOKEN_SECRET)
                .parseClaimsJws( token )
                .getBody()
                .getSubject();
			
		}
		catch(Exception invalid)
		{
			user = "";
		}
		
		if(user == null)
			return "";
		return user;
	}
	
	
	/**
	 * Gets the uuid.
	 *
	 * @return Generates a Unique String Id
	 */
	public String getUUID()
	{
		return UUID.randomUUID().toString();
	}
	
}
