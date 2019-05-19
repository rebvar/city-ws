package com.rebvar.location_app.backend.ws.util;

/**
 * The Class Util.
 */
public class Util {
	
	/**
	 * Null to str.
	 *
	 * @param value the value to be checked
	 * @return value if it is not null, "" otherwise.
	 */
	public static String nullToStr(String value)
	{
		if (value == null)
			return "";
		return value;
	}
	
}
