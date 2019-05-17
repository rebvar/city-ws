package com.rebvar.location_app.backend.ws.location_app.model.request;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;

/**
 * The Class UserLoginRequestModel.
 */
public class UserLoginRequestModel extends AbstractUserModel {

	/** The password. */
	private String password;

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
