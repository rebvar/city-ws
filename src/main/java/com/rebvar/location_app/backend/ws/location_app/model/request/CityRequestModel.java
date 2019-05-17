package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;


/**
 * The Class CityRequestModel.
 */
public class CityRequestModel extends AbstractCityModel {
	
	/** The country name. */
	private String countryName;
	
	/** The continent name. */
	private String continentName;
	
	/** The country unique id. */
	private String countryUniqueId;
	
	/** The continent unique id. */
	private String continentUniqueId;
	
	/**
	 * Gets the country name.
	 *
	 * @return the country name
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * Sets the country name.
	 *
	 * @param countryName the new country name
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
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
	 * Gets the country unique id.
	 *
	 * @return the country unique id
	 */
	public String getCountryUniqueId() {
		return countryUniqueId;
	}
	
	/**
	 * Sets the country unique id.
	 *
	 * @param countryUniqueId the new country unique id
	 */
	public void setCountryUniqueId(String countryUniqueId) {
		this.countryUniqueId = countryUniqueId;
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
