package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;


/**
 * The Class UserDTO.
 */
public class UserDTO extends AbstractUserModel implements Serializable {
	
	/** The user id. */
	private String userId;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6154583116824995239L;
	
	/** The password. */
	private String password;
    
    /** The encrypted password. */
    private String encryptedPassword;
    
	
	
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

	/**
	 * Sets the encrypted password.
	 *
	 * @param encryptedPassword the new encrypted password
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		
		this.encryptedPassword = encryptedPassword;
	}
	
	/**
	 * Gets the encrypted password.
	 *
	 * @return the encrypted password
	 */
	public String getEncryptedPassword() {
		
		return encryptedPassword;
	}
}
