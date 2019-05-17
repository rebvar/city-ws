package com.rebvar.location_app.backend.ws.location_app.model.response;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;

/**
 * The Class UserDataResponseModel.
 */
public class UserDataResponseModel extends AbstractUserModel {
	
	/** The user id. */
	private String userId;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
