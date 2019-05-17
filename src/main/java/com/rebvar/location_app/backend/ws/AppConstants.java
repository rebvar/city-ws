package com.rebvar.location_app.backend.ws;

public class AppConstants {
	
	public static final long EXPIRATION_TIME = 864000000;  
    public static final String TOKEN_PREFIX = "TOKEN_";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String LOGIN_URL = "/users/login";
    public static final String MAP_CITY_ENDPOINT = "/map/city/**";
    public static final String MAP_CONTINENT_ENDPOINT = "/map/continent/**";
    public static final String MAP_COUNTRY_ENDPOINT = "/map/country/**";
    public static final String MAP_SEARCH_ENDPOINT = "/map/search/**";
    public static final String MAP_ENDPOINT = "/map/**";
    public static final String INVALID_AUTH_DEFAULT_VALUE = "!INVALID_AUTH_TOKEN!";
    
    public static final String INVALID_ENDPOINT = "/invalid";
    public static final String H2_CONSOLE = "/h2-console/**";
    public static String TOKEN_SECRET;
    public static boolean SECURITY_ENABLED=false;
	
}
