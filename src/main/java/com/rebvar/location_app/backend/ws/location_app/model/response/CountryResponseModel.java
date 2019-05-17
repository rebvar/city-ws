package com.rebvar.location_app.backend.ws.location_app.model.response;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;

/**
 * The Class CountryResponseModel.
 */
public class CountryResponseModel extends AbstractCityModel {
	
	/** The unique id. */
	private String uniqueId;
	
	/** The continent id. */
	private String continentId;
    
    /** The continent name. */
    private String continentName;
    
	/**
	 * Gets the continent name.
	 *
	 * @return the continent name
	 */
	public String getContinentName() {
		return continentName;
	}
	
	/**
	 * Sets the continent name.
	 *
	 * @param continentName the new continent name
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	
	/**
	 * Sets the unique id.
	 *
	 * @param uniqueId the new unique id
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	/**
	 * Gets the continent id.
	 *
	 * @return the continent id
	 */
	public String getContinentId() {
		return continentId;
	}
	
	/**
	 * Sets the continent id.
	 *
	 * @param continentId the new continent id
	 */
	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}
}
