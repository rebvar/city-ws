package com.rebvar.location_app.backend.ws.location_app.model.response;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;

public class CountryResponseModel extends AbstractCityModel {
	
	private String uniqueId;
	private String continentId;
    private String continentName;
    
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
	public String getContinentId() {
		return continentId;
	}
	public void setContinentId(String continentId) {
		this.continentId = continentId;
	}
}
