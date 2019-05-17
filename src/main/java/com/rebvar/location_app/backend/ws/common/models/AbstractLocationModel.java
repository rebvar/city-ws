package com.rebvar.location_app.backend.ws.common.models;

/**
 * The Class AbstractLocationModel.
 */
public abstract class AbstractLocationModel {
	
	/** The name. */
	//Name for the location
    protected String name;
    
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
