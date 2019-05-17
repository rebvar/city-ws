package com.rebvar.location_app.backend.ws;


// TODO: Auto-generated Javadoc
/**
 * The Class AppConstants.
 *
 * @author Rebvar
 */
public class AppConstants {
	
	/** The Constant EXPIRATION_TIME. */
	public static final long EXPIRATION_TIME = 864000000;  
    
    /** The Constant TOKEN_PREFIX. Start of the the authorization header */
    public static final String TOKEN_PREFIX = "TOKEN_";
    
    /** The Constant HEADER_STRING for authorization */
    public static final String HEADER_STRING = "Authorization";
    
    /** The Constant SIGN_UP_URL. */
    public static final String SIGN_UP_URL = "/users";
    
    /** The Constant LOGIN_URL. */
    public static final String LOGIN_URL = "/users/login";
    
    /** The Constant MAP_CITY_ENDPOINT. */
    public static final String MAP_CITY_ENDPOINT = "/map/city/**";
    
    /** The Constant MAP_CONTINENT_ENDPOINT. */
    public static final String MAP_CONTINENT_ENDPOINT = "/map/continent/**";
    
    /** The Constant MAP_COUNTRY_ENDPOINT. */
    public static final String MAP_COUNTRY_ENDPOINT = "/map/country/**";
    
    /** The Constant MAP_SEARCH_ENDPOINT. */
    public static final String MAP_SEARCH_ENDPOINT = "/map/search/**";
    
    /** The Constant MAP_ENDPOINT. */
    public static final String MAP_ENDPOINT = "/map/**";
    
    /** The Constant INVALID_AUTH_DEFAULT_VALUE. */
    public static final String INVALID_AUTH_DEFAULT_VALUE = "!INVALID_AUTH_TOKEN!";
    
    /** The Constant INVALID_ENDPOINT. */
    public static final String INVALID_ENDPOINT = "/invalid";
    
    /** The Constant H2_CONSOLE. */
    public static final String H2_CONSOLE = "/h2-console/**";
    
    /** The token secret. */
    public static String TOKEN_SECRET;
    
    /** The security enabled. */
    public static boolean SECURITY_ENABLED=false;
}
