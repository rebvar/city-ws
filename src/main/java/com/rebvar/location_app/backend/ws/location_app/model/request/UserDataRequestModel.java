package com.rebvar.location_app.backend.ws.location_app.model.request;


import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;

public class UserDataRequestModel extends AbstractUserModel {
	
	protected String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
