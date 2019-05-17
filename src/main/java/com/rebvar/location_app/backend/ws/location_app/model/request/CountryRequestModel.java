package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractLocationModel;

public class CountryRequestModel extends AbstractLocationModel {
	
	private String continentName;
	private String continentUniqueId;
	
	public String getContinentName() {
		return continentName;
	}
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	public String getContinentUniqueId() {
		return continentUniqueId;
	}
	public void setContinentUniqueId(String continentUniqueId) {
		this.continentUniqueId = continentUniqueId;
	}
}
