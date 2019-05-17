package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;

public class CityRequestModel extends AbstractCityModel {
	
	private String countryName;
	private String continentName;
	private String countryUniqueId;
	private String continentUniqueId;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getContinentName() {
		return continentName;
	}
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	public String getCountryUniqueId() {
		return countryUniqueId;
	}
	public void setCountryUniqueId(String countryUniqueId) {
		this.countryUniqueId = countryUniqueId;
	}
	public String getContinentUniqueId() {
		return continentUniqueId;
	}
	public void setContinentUniqueId(String continentUniqueId) {
		this.continentUniqueId = continentUniqueId;
	}
}
