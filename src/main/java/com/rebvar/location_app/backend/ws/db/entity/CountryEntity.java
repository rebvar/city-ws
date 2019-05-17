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


@Entity(name="country")
public class CountryEntity implements Serializable {
	
	private static final long serialVersionUID = 8225342489296017278L;

	@Id
	@GeneratedValue
	private long id;
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Column(nullable = false, unique = true)
	private String uniqueId;
		
	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@ManyToOne
    @JoinColumn(name="continent_id")
    private ContinentEntity continent;

	@Column(nullable = false, unique=true)
	private String name;

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	
	private List<CityEntity> cities;
	
	public ContinentEntity getContinent() { 
		return continent;
	}

	public void setContinent(ContinentEntity continent) {
		this.continent = continent;
	}

	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}
	
	public void AddCity(CityEntity city)
	{
		if (cities == null)
			cities = new ArrayList<CityEntity>();
		this.cities.add(city);
	}
}
