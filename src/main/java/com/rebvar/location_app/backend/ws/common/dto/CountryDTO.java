package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;


public class CountryDTO extends AbstractLocationDTO implements Serializable {
	
	private static final long serialVersionUID = 9203794807747131855L;
	
	private ContinentDTO continent;
	
	public ContinentDTO getContinent() {
		return continent;
	}

	public void setContinent(ContinentDTO continent) {
		this.continent = continent;
	}	
}
