package com.rebvar.location_app.backend.ws.common.models;

public abstract class AbstractLocationModel {
	
	//Name for the location
    protected String name;
    
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
