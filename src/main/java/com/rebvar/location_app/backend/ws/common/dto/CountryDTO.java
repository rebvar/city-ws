package com.rebvar.location_app.backend.ws.common.dto;

import java.io.Serializable;


/**
 * The Class CountryDTO.
 */
public class CountryDTO extends AbstractLocationDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9203794807747131855L;
	
	/** The continent. */
	private ContinentDTO continent;
	
	/**
	 * Gets the continent.
	 *
	 * @return the continent
	 */
	public ContinentDTO getContinent() {
		return continent;
	}

	/**
	 * Sets the continent.
	 *
	 * @param continent the new continent
	 */
	public void setContinent(ContinentDTO continent) {
		this.continent = continent;
	}	
}
