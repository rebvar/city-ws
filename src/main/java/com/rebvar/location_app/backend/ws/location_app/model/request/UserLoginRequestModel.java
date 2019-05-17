package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;

public class UserLoginRequestModel extends AbstractUserModel {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
