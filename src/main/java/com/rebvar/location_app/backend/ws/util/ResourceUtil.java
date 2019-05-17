package com.rebvar.location_app.backend.ws.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.rebvar.location_app.backend.ws.AppConstants;


/**
 * The Class ResourceUtil.
 */
public class ResourceUtil {
	
	/**
	 * Load application constants.
	 */
	public static void LoadApplicationConstants() {
	    
	    try {
	    	String dataStr = openResource("application.properties");
			  		      	    	
	    	String lines[] = dataStr.split("\n");

	        
		    for (String line : lines)
		    {
		    	line  = line.replace("\n", "").replace("\r", "");
		    	
		    	if (line.startsWith("token_secret="))
		    	{
		    	 	AppConstants.TOKEN_SECRET = line.split("=")[1];
		    	}
		    	else if (line.startsWith("security_enabled="))
		    	{
		    	 	AppConstants.SECURITY_ENABLED = Boolean.parseBoolean(line.split("=")[1]);
		    	}
		    }
	    }
	    catch(Exception ex)
	    {
	    	System.err.println("Failed Reading Conf...");
	    }
	}
	
	/**
	 * Read country data.
	 *
	 * @return the list
	 */
	public static List<String[]> ReadCountryData() {
		
		List<String[]> ret = new ArrayList<String[]>();
		
	    try {
	    	
	    	String dataStr = openResource("data/data.txt");
	    	
	    	String lines[] = dataStr.split("\n");
	        
		    for (String line : lines)
		    {
		    	if (line.length()>3)
		    		ret.add(line.replace("\n", "").replace("\r", "").split(","));
		    }
		    
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Failed reading bulk data: "+ ex);
	    }
	    
	    return ret;
	}

	/**
	 * Open resource.
	 *
	 * @param name the name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	private static String openResource(String name) throws IOException, FileNotFoundException, UnsupportedEncodingException {
		String dataStr = "";
		
		
		try {
			InputStream resource = new ClassPathResource("classpath:"+name).getInputStream();
		
			BufferedInputStream bis = new BufferedInputStream(resource);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result = bis.read();
			while(result != -1) {
			    buf.write((byte) result);
			    result = bis.read();
			}

			dataStr = buf.toString("UTF-8");
			
			resource.close();
			bis.close();
			buf.close();
		}
		catch(Exception ex)
		{
			File resource = new ClassPathResource(
		  		      name).getFile();
			    
			FileInputStream reader = new FileInputStream(resource);
		
		   
			byte[] data = new byte[(int) resource.length()];
			reader.read(data);
			reader.close();

			dataStr = new String(data, "UTF-8");
			reader.close();
		}
		return dataStr;
	}
}
