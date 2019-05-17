package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;

import com.rebvar.location_app.backend.ws.common.models.AbstractLocationModel;

/**
 * The Class for Abstract Location DTO.
 */
public abstract class AbstractLocationDTO extends AbstractLocationModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5206934543058726315L;

	/** The unique id. */
	private String uniqueId;

	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * Sets the unique id.
	 *
	 * @param uniqueId the new unique id
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
}
