package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractLocationModel;

/**
 * The Class CountryRequestModel.
 */
public class CountryRequestModel extends AbstractLocationModel {
	
	/** The continent name. */
	private String continentName;
	
	/** The continent unique id. */
	private String continentUniqueId;
	
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
	 * Gets the continent unique id.
	 *
	 * @return the continent unique id
	 */
	public String getContinentUniqueId() {
		return continentUniqueId;
	}
	
	/**
	 * Sets the continent unique id.
	 *
	 * @param continentUniqueId the new continent unique id
	 */
	public void setContinentUniqueId(String continentUniqueId) {
		this.continentUniqueId = continentUniqueId;
	}
}
