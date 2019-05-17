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
 * @author Rebvar
 *
 * Implements the LocationService interface as service. 
 * Performs various operations for location data and presenting the
 *  results through Data transfer objects.
 */

@Service
public class LocationServiceImpl implements LocationService{

	//CrudRepositories for various operations on location data
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	ContinentRepository continentRepository;
	
		
	//Accessing the userRepository to save the searches and manage the favorite operations.
	@Autowired
	UserRepository userRepository;
	
	
	//for generating unique ids. 
	@Autowired 
	SecurityUtils sutils;
	
	private UserEntity getUser(String userId) {
		UserEntity user = userRepository.findByUserId(userId);
		if (user==null && userId.length()>0)
			throw new RuntimeException("Invalid User Id");
		return user;
	}


	@Override
	public CityDTO getCity(String uniqueId, String userId) {
		UserEntity user = getUser(userId);
		
		CityEntity city = cityRepository.findByUniqueId(uniqueId);
		if (city==null)
			return null;
				
		CityDTO cityDto = new ModelMapper().map(city, CityDTO.class);
		return cityDto;
	}
	
	@Override
	public CountryDTO getCountry(String uniqueId, String userId) {
		
		UserEntity user = getUser(userId);
		CityEntity country = cityRepository.findByUniqueId(uniqueId);
		if (country==null)
			throw new RuntimeException("No country found for the specified id");
				
		CountryDTO countryDto = new ModelMapper().map(country, CountryDTO.class);
		return countryDto;
	}


	@Override
	public ContinentDTO getContinent(String uniqueId, String userId) {
		UserEntity user = getUser(userId);
		ContinentEntity continent = continentRepository.findByUniqueId(uniqueId);
		if (continent==null)
			throw new RuntimeException("No continent found for the specified id");
				
		ContinentDTO continentDto = new ModelMapper().map(continent, ContinentDTO.class);
		return continentDto;
	}


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
	
	@Override
	public List<CityDTO> searchCity(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<CityEntity> cities = cityRepository.findCitiesByPattern(name);
		List<CityDTO> ret = new ArrayList<CityDTO>();
        ModelMapper mapper = new ModelMapper();
		cities.forEach(city-> ret.add(mapper.map(city, CityDTO.class)));
		return ret;
	}

	@Override
	public CityDTO deleteFromFavorites(String uniqueId, String userId) {
		return null;
	}

	@Override
	public CityDTO addToFavorites(String uniqueId, String userId) {		
		return null;
	}

	
	
	
	@Override
	public List<ContinentDTO> saveContinent(ContinentRequestModel continentData, String userId)
	{
		List<ContinentDTO> retContinents = new ArrayList<ContinentDTO>();
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
	
	
	@Override
	public List<CountryDTO> saveCountry(CountryRequestModel countryData, String userId)
	{
		List<CountryDTO> retCountries = new ArrayList<CountryDTO>();
		ModelMapper mapper = new ModelMapper();
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

	@Override
	public List<CityDTO> saveCity(CityRequestModel cityData, String userId) {
		
		
		List<CityDTO> retCities = new ArrayList<CityDTO>();
		if (cityData==null)
			return retCities;
		
		ModelMapper mapper = new ModelMapper();
		
		cityData.setContinentName(Util.nullToStr(cityData.getContinentName()).toLowerCase());
		cityData.setCountryName(Util.nullToStr(cityData.getCountryName()).toLowerCase());
		cityData.setName(Util.nullToStr(cityData.getName()).toLowerCase());
		cityData.setCountryUniqueId(Util.nullToStr(cityData.getCountryUniqueId()));
		cityData.setContinentUniqueId(Util.nullToStr(cityData.getContinentUniqueId()));
		
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


	@Override
	public CityDTO deleteCity(String uniqueId, String userId) {
		
		CityEntity entity = cityRepository.findByUniqueId(uniqueId);
		if (entity == null)
			throw new RuntimeException("The city with the specified Id not found...");
		
		cityRepository.delete(entity);
		return new ModelMapper().map(entity, CityDTO.class);
		
	}


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

	@Override
	public List<ContinentDTO> searchContinent(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<ContinentEntity> entities = continentRepository.findContinentsByPattern(name);
		List<ContinentDTO> ret = new ArrayList<ContinentDTO>();
        ModelMapper mapper = new ModelMapper();
		entities.forEach(entity-> ret.add(mapper.map(entity, ContinentDTO.class)));
		return ret;
	}

	@Override
	public List<CountryDTO> searchCountry(String name, String userId) {
		UserEntity user = getUser(userId);
		Iterable<CountryEntity> entities = countryRepository.findCountriesByPattern(name);
		List<CountryDTO> ret = new ArrayList<CountryDTO>();
        ModelMapper mapper = new ModelMapper();
		entities.forEach(entity-> ret.add(mapper.map(entity, CountryDTO.class)));
		return ret;
	}

	@Override
	public int bulkLoad(int maxCount) {
		int count = 0;
		CityRequestModel city = new CityRequestModel();		
		List<String[]> data = ResourceUtil.ReadCountryData();
		if (maxCount == 0)
			maxCount = data.size();
		//System.out.println("Data size is :"+maxCount);
		//System.out.println("Loaded data is :"+data.size());
		
		for (String[] item : data)
		{
			city.setName(item[2]);
			city.setCountryName(item[1]);
			city.setContinentName(item[0]);
			
			try
			{
				saveCity(city, "");
				//System.out.println("saved :"+city);
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
		//System.out.println("Count after bulk load is :"+count);
		return count;
	}
}
