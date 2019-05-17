package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;

/**
 * The Class CityDTO.
 */
public class CityDTO extends AbstractLocationDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6167331306385634481L;
	
	/** The country. */
	private CountryDTO country;
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public CountryDTO getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(CountryDTO country) {
		this.country = country;
	}	
}
