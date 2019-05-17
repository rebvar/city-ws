package com.rebvar.location_app.backend.ws.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class ContinentEntity.
 */
@Entity(name="continent")
public class ContinentEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8225342489296017278L;
	
	
	/** The id. */
	@Id
	@GeneratedValue
	private long id;
	
	
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
	
	/** The name. */
	@Column(nullable = false, unique = true)
	private String name;

	
	/** The countries. */
	@OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CountryEntity> countries;

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	public List<CountryEntity> getCountries() {
		return countries;
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
	 * Sets the countries.
	 *
	 * @param countries the new countries
	 */
	public void setCountries(List<CountryEntity> countries) {
		this.countries = countries;
	}
	
	/**
	 * Adds the country.
	 *
	 * @param country the country
	 */
	public void AddCountry(CountryEntity country)
	{
		if (countries == null)
			countries = new ArrayList<CountryEntity>();
		this.countries.add(country);
	}	
}
