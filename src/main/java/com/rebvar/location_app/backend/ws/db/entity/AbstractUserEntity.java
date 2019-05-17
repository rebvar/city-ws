package com.rebvar.location_app.backend.ws.db.entity;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.MappedSuperclass;

import com.rebvar.location_app.backend.ws.common.models.AbstractUserModel;

/**
 * The Class AbstractUserEntity.
 */
@MappedSuperclass
public class AbstractUserEntity extends AbstractUserModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8225342489296017278L;
	
	/** The user id. */
	@Column(nullable = false, unique = true)
	private String userId;
	
	/** The encrypted password. */
	@Column(nullable = false)
	private String encryptedPassword;
	
	/** The name. */
	@Column(nullable = false)
	private String name;
	
	/** The email. */
	@Column(nullable = false, unique = true)
	private String email;
	
	
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
	 * Gets the encrypted password.
	 *
	 * @return the encrypted password
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
