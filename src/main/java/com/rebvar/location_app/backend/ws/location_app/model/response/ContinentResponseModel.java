package com.rebvar.location_app.backend.ws.location_app.model.response;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;

/**
 * The Class ContinentResponseModel.
 */
public class ContinentResponseModel extends AbstractCityModel {
	
	/** The unique id. */
	private String uniqueId;
    
	
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
}
