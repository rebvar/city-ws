package com.rebvar.location_app.backend.ws.location_app.model.response;


import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;
public class CityResponseModel extends AbstractCityModel {
	
	private String uniqueId;
	private String countryId;
    private String continentId;
    private String countryName;
    private String continentName;
    
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
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getContinentId() {
		return continentId;
	}
	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}
}
