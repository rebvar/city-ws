package com.rebvar.location_app.backend.ws.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rebvar.location_app.backend.ws.AppConstants;
import com.rebvar.location_app.backend.ws.common.dto.CityDTO;
import com.rebvar.location_app.backend.ws.common.dto.ContinentDTO;
import com.rebvar.location_app.backend.ws.common.dto.CountryDTO;
import com.rebvar.location_app.backend.ws.db.entity.CityEntity;
import com.rebvar.location_app.backend.ws.db.entity.ContinentEntity;
import com.rebvar.location_app.backend.ws.db.entity.CountryEntity;
import com.rebvar.location_app.backend.ws.db.entity.UserEntity;
import com.rebvar.location_app.backend.ws.db.repository.CityRepository;
import com.rebvar.location_app.backend.ws.db.repository.ContinentRepository;
import com.rebvar.location_app.backend.ws.db.repository.CountryRepository;
import com.rebvar.location_app.backend.ws.db.repository.UserRepository;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.ContinentRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.CountryRequestModel;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.service.LocationService;
import com.rebvar.location_app.backend.ws.util.ResourceUtil;
import com.rebvar.location_app.backend.ws.util.Util;

import java.util.ArrayList;
import java.util.List;


import org.json.*;
import org.modelmapper.ModelMapper;

/**
 * The Class LocationServiceImpl.
 *
 * @author Rebvar
 * 
 * Implements the LocationService interface as service. 
 * Performs various operations for location data and presenting the
 *  results through Data transfer objects.
 */

@Service
public class LocationServiceImpl implements LocationService{

	//CrudRepositories for various operations on location data
	
	/** The city repository. */
	@Autowired
	CityRepository cityRepository;
	
	/** The country repository. */
	@Autowired
	CountryRepository countryRepository;
	
	/** The continent repository. */
	@Autowired
	ContinentRepository continentRepository;
	
		
	/** The user repository. */
	//Accessing the userRepository to save the searches and manage the favorite operations.
	@Autowired
	UserRepository userRepository;
	
	
	/** The sutils. */
	//for generating unique ids. 
	@Autowired 
	SecurityUtils sutils;
	
	/**
	 * Gets the user.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	private UserEntity getUser(String userId) {
		UserEntity user = userRepository.findByUserId(userId);
		if (user==null && userId.length()>0)
			throw new RuntimeException("Invalid User Id");
		return user;
	}


	/**
	 * Gets the city.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city
	 */
	@Override
	public CityDTO getCity(String uniqueId, String userId) {
		UserEntity user = getUser(userId);
		
		CityEntity city = cityRepository.findByUniqueId(uniqueId);
		if (city==null)
			return null;
				
		CityDTO cityDto = new ModelMapper().map(city, CityDTO.class);
		return cityDto;
	}
	
	/**
	 * Gets the country.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the country
	 */
	@Override
	public CountryDTO getCountry(String uniqueId, String userId) {
		
		UserEntity user = getUser(userId);
		CityEntity country = cityRepository.findByUniqueId(uniqueId);
		if (country==null)
			throw new RuntimeException("No country found for the specified id");
				
		CountryDTO countryDto = new ModelMapper().map(country, CountryDTO.class);
		return countryDto;
	}


	/**
	 * Gets the continent.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the continent
	 */
	@Override
	public ContinentDTO getContinent(String uniqueId, String userId) {
		UserEntity user = getUser(userId);
		ContinentEntity continent = continentRepository.findByUniqueId(uniqueId);
		if (continent==null)
			throw new RuntimeException("No continent found for the specified id");
				
		ContinentDTO continentDto = new ModelMapper().map(continent, ContinentDTO.class);
		return continentDto;
	}


	/**
	 * Search city by country.
	 *
	 * @param name the name
	 * @param countryId the country id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> searchCityByCountry(String name, String countryId, String userId) {
		CountryEntity countryEntity = countryRepository.findByUniqueId(countryId);
		if (countryEntity == null)
		{
			throw new RuntimeException("Invalid country Id...");
		}
		UserEntity user = getUser(userId);
		Iterable<CityEntity> cities = cityRepository.findCitiesByPartialNameAndCountryId(name, countryEntity.getId());
		List<CityDTO> ret = new ArrayList<CityDTO>();
        ModelMapper mapper = new ModelMapper();
		cities.forEach(city-> ret.add(mapper.map(city, CityDTO.class)));
		return ret;
	}
	
	/**
	 * Search country by continent.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CountryDTO> searchCountryByContinent(String name, String continentId, String userId) {
		ContinentEntity continentEntity = continentRepository.findByUniqueId(continentId);
		if (continentEntity == null)
		{
			throw new RuntimeException("Invalid continent Id...");
		}
		
		UserEntity user = getUser(userId);
		Iterable<CountryEntity> countries = countryRepository.findCountriesByPartialNameAndContinentId(name, continentEntity.getId());
		List<CountryDTO> ret = new ArrayList<CountryDTO>();
        ModelMapper mapper = new ModelMapper();
        countries.forEach(country-> ret.add(mapper.map(country, CountryDTO.class)));
		return ret;
	}
	
	
	/**
	 * Search city by continent.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> searchCityByContinent(String name, String continentId, String userId) {
		ContinentEntity continentEntity = continentRepository.findByUniqueId(continentId);
		if (continentEntity == null)
		{
			throw new RuntimeException("Invalid continent Id...");
		}
		UserEntity user = getUser(userId);
		Iterable<CityEntity> cities = cityRepository.findCitiesByPartialNameAndContinentId(name, continentEntity.getId());
		List<CityDTO> ret = new ArrayList<CityDTO>();
        ModelMapper mapper = new ModelMapper();
		cities.forEach(city-> ret.add(mapper.map(city, CityDTO.class)));
		return ret;
	}
	
	/**
	 * Search city.
	 *
	 * @param name the name
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> searchCity(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<CityEntity> cities = cityRepository.findCitiesByPattern(name);
		List<CityDTO> ret = new ArrayList<CityDTO>();
        ModelMapper mapper = new ModelMapper();
		cities.forEach(city-> ret.add(mapper.map(city, CityDTO.class)));
		return ret;
	}

	/**
	 * Delete from favorites.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	@Override
	public CityDTO deleteFromFavorites(String uniqueId, String userId) {
		return null;
	}

	/**
	 * Adds the to favorites.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	@Override
	public CityDTO addToFavorites(String uniqueId, String userId) {		
		return null;
	}

	
	
	
	/**
	 * Save continent.
	 *
	 * @param continentData the continent data
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<ContinentDTO> saveContinent(ContinentRequestModel continentData, String userId)
	{
		List<ContinentDTO> retContinents = new ArrayList<ContinentDTO>();
		if (continentData== null )
			return retContinents;
		
		continentData.setName(Util.nullToStr(continentData.getName()).toLowerCase());
		
		ModelMapper mapper = new ModelMapper();
		
		ContinentEntity continentEntity = null;
		String continentId;
		continentEntity = continentRepository.findByName(continentData.getName().toLowerCase());
		if (continentEntity!=null)
		{
			continentId = continentEntity.getUniqueId();
			retContinents.add(mapper.map(continentEntity, ContinentDTO.class));
			return retContinents;
		}
		
		
		continentEntity = new ContinentEntity();
		continentEntity.setName(continentData.getName().toLowerCase());
		continentId = sutils.getUUID();
		continentEntity.setUniqueId(continentId);
		continentRepository.save(continentEntity);
		retContinents.add(mapper.map(continentEntity, ContinentDTO.class));
		return retContinents;
	}
	
	
	/**
	 * Save country.
	 *
	 * @param countryData the country data
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CountryDTO> saveCountry(CountryRequestModel countryData, String userId)
	{
		List<CountryDTO> retCountries = new ArrayList<CountryDTO>();
		
		if (countryData==null)
			return retCountries;
		
		ModelMapper mapper = new ModelMapper();
		
		countryData.setContinentName(Util.nullToStr(countryData.getContinentName()).toLowerCase());
		countryData.setName(Util.nullToStr(countryData.getName()).toLowerCase());
		countryData.setContinentUniqueId(Util.nullToStr(countryData.getContinentUniqueId()));
		
		
		String continentId = Util.nullToStr(countryData.getContinentUniqueId());
		
		
		ContinentEntity continentEntity = null;
		CountryEntity countryEntity;
		
		if (!continentId.isEmpty())
		{
			continentEntity = continentRepository.findByUniqueId(continentId);
			if (continentEntity==null)
			{
				throw new RuntimeException("Invalid continent Id specified when posting new city...");
			}
		}
		else
		{
			continentEntity = continentRepository.findByName(countryData.getContinentName().toLowerCase());
			if (continentEntity!=null)
			{
				continentId = continentEntity.getUniqueId();
			}
			
			if (continentEntity ==  null)
			{
				continentEntity = new ContinentEntity();
				continentEntity.setName(countryData.getContinentName().toLowerCase());
				continentId = sutils.getUUID();
				continentEntity.setUniqueId(continentId);
			}
		}
		
		countryEntity = new CountryEntity();
		countryEntity.setName(countryData.getName().toLowerCase());
		countryEntity.setContinent(continentEntity);
		String countryId = sutils.getUUID();
		countryEntity.setUniqueId(countryId);
		continentEntity.AddCountry(countryEntity);
		continentRepository.save(continentEntity);
		
		
		CountryDTO countryDto = mapper.map(countryEntity, CountryDTO.class);
		retCountries.add(countryDto);
		return retCountries;
	}

	/**
	 * Save city.
	 *
	 * @param cityData the city data
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> saveCity(CityRequestModel cityData, String userId) {
		
		
		List<CityDTO> retCities = new ArrayList<CityDTO>();
		if (cityData==null)
			return retCities;
		
		ModelMapper mapper = new ModelMapper();
		
		validateCityRequestModel(cityData);
		
		String countryId = Util.nullToStr(cityData.getCountryUniqueId());
		String continentId = Util.nullToStr(cityData.getContinentUniqueId());
		
		ContinentEntity continentEntity = null;
		CountryEntity countryEntity;
		
		continentEntity = findContinent(cityData, continentId);
		
		if (continentEntity!=null)
		{
			continentId = continentEntity.getUniqueId();
		}
		
		
		if (!countryId.isEmpty())
		{
			countryEntity = countryRepository.findByUniqueId(countryId);
			if (countryEntity==null)
			{
				throw new RuntimeException("Invalid country ID specified when posting a city...");
			}
			
			if (continentId.isEmpty())
			{
				continentEntity = countryEntity.getContinent();
				continentId = continentEntity.getUniqueId();
			}
			else
			{
				if (continentId!=continentEntity.getUniqueId())
				{
					throw new RuntimeException("Invalid country continent Id pair specified...");
				}
			}
			
		}
		else
		{
			countryEntity = countryRepository.findByName(cityData.getCountryName().toLowerCase());
			
			if (countryEntity!= null)
			{
				continentEntity = countryEntity.getContinent();
				continentId = continentEntity.getUniqueId();
				countryId = countryEntity.getUniqueId();
			}
			
			if (continentId.isEmpty())
			{	
				continentEntity = newContinent(cityData);
			}
			
			if (countryEntity == null)
			{
				countryEntity = newCounty(cityData, continentEntity);
			}
		}
		
		if (countryEntity==null || continentEntity == null)
		{
			throw new RuntimeException("Bad continent/country data...");
		}
		
		List<CityEntity> cityEntites = cityRepository.findCitiesByExactNameAndCountryId(cityData.getName().toLowerCase(),countryEntity.getId());
		
		if (cityEntites.size()>0)
		{
			cityEntites.forEach(cityEntity-> retCities.add(mapper.map(cityEntity, CityDTO.class)));
			return retCities;
		}
		
		CityEntity cityEntity = newCity(cityData, countryEntity);
		retCities.add(mapper.map(cityEntity, CityDTO.class));
		return retCities;
	}


	public void validateCityRequestModel(CityRequestModel cityData) {
		cityData.setContinentName(Util.nullToStr(cityData.getContinentName()).toLowerCase());
		cityData.setCountryName(Util.nullToStr(cityData.getCountryName()).toLowerCase());
		cityData.setName(Util.nullToStr(cityData.getName()).toLowerCase());
		cityData.setCountryUniqueId(Util.nullToStr(cityData.getCountryUniqueId()));
		cityData.setContinentUniqueId(Util.nullToStr(cityData.getContinentUniqueId()));
	}


	/**
	 * New city.
	 *
	 * @param cityData the city data
	 * @param countryEntity the country entity
	 * @return the city entity
	 */
	private CityEntity newCity(CityRequestModel cityData, CountryEntity countryEntity) {
		if (cityData.getName().length()<=0)
			throw new RuntimeException("Invalid city name...");
		
		CityEntity cityEntity = new CityEntity();
		cityEntity.setName(cityData.getName().toLowerCase());
		cityEntity.setCountry(countryEntity);
		cityEntity.setUniqueId(sutils.getUUID());
		countryEntity.AddCity(cityEntity);
		cityRepository.save(cityEntity);
		countryRepository.save(countryEntity);
		return cityEntity;
	}


	/**
	 * New continent.
	 *
	 * @param cityData the city data
	 * @return the continent entity
	 */
	private ContinentEntity newContinent(CityRequestModel cityData) {
		
		if (cityData.getContinentName().length()<=0)
			throw new RuntimeException("Invalid continent name...");
		
		String continentId;
		ContinentEntity continentEntity;
		continentEntity = new ContinentEntity();
		continentEntity.setName(cityData.getContinentName().toLowerCase());
		continentId = sutils.getUUID();
		continentEntity.setUniqueId(continentId);
		continentRepository.save(continentEntity);
		return continentEntity;
	}


	/**
	 * New county.
	 *
	 * @param cityData the city data
	 * @param continentEntity the continent entity
	 * @return the country entity
	 */
	private CountryEntity newCounty(CityRequestModel cityData, ContinentEntity continentEntity) {
		
		if (cityData.getCountryName().length()<=0)
			throw new RuntimeException("Invalid country name...");
		
		
		String countryId;
		CountryEntity countryEntity;
		countryEntity = new CountryEntity();
		countryEntity.setName(cityData.getCountryName().toLowerCase());
		countryEntity.setContinent(continentEntity);
		countryId = sutils.getUUID();
		countryEntity.setUniqueId(countryId);
		continentEntity.AddCountry(countryEntity);
		countryRepository.save(countryEntity);
		continentRepository.save(continentEntity);
		return countryEntity;
		
	}

	
	/**
	 * Find continent.
	 *
	 * @param cityData the city data
	 * @param continentId the continent id
	 * @return the continent entity
	 */
	public ContinentEntity findContinent(CityRequestModel cityData, String continentId) {
		ContinentEntity continentEntity;
		if (!continentId.isEmpty())
		{
			continentEntity = continentRepository.findByUniqueId(continentId);
			if (continentEntity==null)
			{
				throw new RuntimeException("Invalid continent Id specified when posting new city...");
			}
		}
		else
		{
			continentEntity = continentRepository.findByName(cityData.getContinentName().toLowerCase());
		}
		return continentEntity;
	}


	/**
	 * Delete city.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	@Override
	public CityDTO deleteCity(String uniqueId, String userId) {
		
		CityEntity entity = cityRepository.findByUniqueId(uniqueId);
		if (entity == null)
			throw new RuntimeException("The city with the specified Id not found...");
		
		cityRepository.delete(entity);
		return new ModelMapper().map(entity, CityDTO.class);
		
	}


	/**
	 * Delete country.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> deleteCountry(String uniqueId, String userId) {
		CountryEntity entity = countryRepository.findByUniqueId(uniqueId);
		
		if (entity == null)
			throw new RuntimeException("The city with the specified Id not found...");
		
		ModelMapper mapper= new ModelMapper();
		List<CityDTO> deletedCities = new ArrayList<CityDTO>();
		entity.getCities().forEach(city->deletedCities.add(mapper.map(city, CityDTO.class)));
		
		countryRepository.delete(entity);
		return deletedCities;		
	}


	/**
	 * Delete continent.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CityDTO> deleteContinent(String uniqueId, String userId) {
		ContinentEntity entity = continentRepository.findByUniqueId(uniqueId);
		if (entity == null)
			throw new RuntimeException("The city with the specified Id not found...");
		
		ModelMapper mapper= new ModelMapper();
		List<CityDTO> deletedCities = new ArrayList<CityDTO>();
		entity.getCountries().forEach(country-> country.getCities()
				.forEach(city->deletedCities.add(mapper.map(city, CityDTO.class))));
		continentRepository.delete(entity);
		return deletedCities;
	}

	/**
	 * Search continent.
	 *
	 * @param name the name
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<ContinentDTO> searchContinent(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<ContinentEntity> entities = continentRepository.findContinentsByPattern(name);
		List<ContinentDTO> ret = new ArrayList<ContinentDTO>();
        ModelMapper mapper = new ModelMapper();
		entities.forEach(entity-> ret.add(mapper.map(entity, ContinentDTO.class)));
		return ret;
	}

	/**
	 * Search country.
	 *
	 * @param name the name
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<CountryDTO> searchCountry(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<CountryEntity> entities = countryRepository.findCountriesByPattern(name);
		List<CountryDTO> ret = new ArrayList<CountryDTO>();
        ModelMapper mapper = new ModelMapper();
		entities.forEach(entity-> ret.add(mapper.map(entity, CountryDTO.class)));
		return ret;
	}

	/**
	 * Loads from Resources.
	 *
	 * @param maxCount the max count
	 * @return the int
	 */
	@Override
	public int loadFromResources(int maxCount) {
		int count = 0;
		CityRequestModel city = new CityRequestModel();		
		List<String[]> data = ResourceUtil.ReadCountryData();
		if (maxCount == 0)
			maxCount = data.size();
		
		for (String[] item : data)
		{
			city.setName(item[2]);
			city.setCountryName(item[1]);
			city.setContinentName(item[0]);
			
			try
			{
				saveCity(city, "");
				count+=1;
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
				//Log the error, skip for now
			}
			
			if (count>=maxCount)
				break;
		}
		return count;
	}
}
