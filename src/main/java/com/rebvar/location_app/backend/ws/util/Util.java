package com.rebvar.location_app.backend.ws.util;

/**
 * The Class Util.
 */
public class Util {
	
	/**
	 * Null to str.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String nullToStr(String value)
	{
		if (value == null)
			return "";
		return value;
	}
	
}
