package com.rebvar.location_app.backend.ws.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The Class CityEntity.
 */
@Entity(name="city")
public class CityEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8225342489296017278L;

	
	/** The id. */
	@Id
	@GeneratedValue
	private long id;
	
	
	/** The unique id. */
	@Column(nullable = false, unique = true)
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
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** The name. */
	@Column(nullable = false)
	private String name;

	
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

	/** The country. */
	@ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public CountryEntity getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(CountryEntity country) {
		this.country = country;
	}
}
