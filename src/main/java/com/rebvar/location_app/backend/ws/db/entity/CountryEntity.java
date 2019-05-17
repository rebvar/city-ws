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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The Class CountryEntity.
 */
@Entity(name="country")
public class CountryEntity implements Serializable {
	
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
	
	/** The continent. */
	@ManyToOne
    @JoinColumn(name="continent_id")
    private ContinentEntity continent;

	/** The name. */
	@Column(nullable = false, unique=true)
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

	/** The cities. */
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	
	private List<CityEntity> cities;
	
	/**
	 * Gets the continent.
	 *
	 * @return the continent
	 */
	public ContinentEntity getContinent() { 
		return continent;
	}

	/**
	 * Sets the continent.
	 *
	 * @param continent the new continent
	 */
	public void setContinent(ContinentEntity continent) {
		this.continent = continent;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	public List<CityEntity> getCities() {
		return cities;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities the new cities
	 */
	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}
	
	/**
	 * Adds the city.
	 *
	 * @param city the city
	 */
	public void AddCity(CityEntity city)
	{
		if (cities == null)
			cities = new ArrayList<CityEntity>();
		this.cities.add(city);
	}
}
