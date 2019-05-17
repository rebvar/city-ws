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

@Entity(name="continent")
public class ContinentEntity implements Serializable {
	
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
	
	@Column(nullable = false, unique = true)
	private String name;

	
	@OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CountryEntity> countries;

	public List<CountryEntity> getCountries() {
		return countries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountries(List<CountryEntity> countries) {
		this.countries = countries;
	}
	
	public void AddCountry(CountryEntity country)
	{
		if (countries == null)
			countries = new ArrayList<CountryEntity>();
		this.countries.add(country);
	}	
}
