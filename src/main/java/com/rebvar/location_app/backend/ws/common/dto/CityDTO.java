package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;


public class CityDTO extends AbstractLocationDTO implements Serializable {
	
	private static final long serialVersionUID = -5206934543058726315L;

	private CountryDTO country;
	
	public CountryDTO getCountry() {
		return country;
	}
	
	public void setCountry(CountryDTO country) {
		this.country = country;
	}	
}
