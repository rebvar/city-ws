package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;


public class UserDTO extends AbstractUserModel implements Serializable {
	
	private String userId;
	private static final long serialVersionUID = 6154583116824995239L;
	private String password;
    private String encryptedPassword;
    
	
	
    public String getUserId() {
		return userId;
	}
    
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		
		this.encryptedPassword = encryptedPassword;
	}
	
	public String getEncryptedPassword() {
		
		return encryptedPassword;
	}
}
