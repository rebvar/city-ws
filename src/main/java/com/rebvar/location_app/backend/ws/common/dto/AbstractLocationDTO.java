package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;

import com.rebvar.location_app.backend.ws.common.models.AbstractLocationModel;


public abstract class AbstractLocationDTO extends AbstractLocationModel implements Serializable {
	
	private static final long serialVersionUID = -5206934543058726315L;

	private String uniqueId;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
}
