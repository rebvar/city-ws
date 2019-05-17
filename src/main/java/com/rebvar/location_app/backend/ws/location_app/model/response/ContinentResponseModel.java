package com.rebvar.location_app.backend.ws.location_app.model.response;

import com.rebvar.location_app.backend.ws.common.models.AbstractCityModel;

public class ContinentResponseModel extends AbstractCityModel {
	
	private String uniqueId;
    
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
