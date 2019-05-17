package com.rebvar.location_app.backend.ws.service;

import java.util.List;

import com.rebvar.location_app.backend.ws.common.dto.CityDTO;
import com.rebvar.location_app.backend.ws.common.dto.ContinentDTO;
import com.rebvar.location_app.backend.ws.common.dto.CountryDTO;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.ContinentRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.CountryRequestModel;

/**
 * The Interface LocationService.
 *
 * @author sehossei
 * Weather Service. In charge of search, storage and retrieval of the location weather processes.
 */
/**
 * @author Rebvar
 *
 */
public interface LocationService {

	/**
	 * Gets the city.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city
	 */
	public CityDTO getCity(String uniqueId, String userId);
	
	/**
	 * Gets the country.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the country
	 */
	public CountryDTO getCountry(String uniqueId, String userId);
	
	/**
	 * Gets the continent.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the continent
	 */
	public ContinentDTO getContinent(String uniqueId, String userId);	
	
	/**
	 * Adds the to favorites.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	public CityDTO addToFavorites(String uniqueId, String userId);
	
	/**
	 * Delete from favorites.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	public CityDTO deleteFromFavorites(String uniqueId, String userId);
	
	
	/**
	 * Save city.
	 *
	 * @param cityData the city data
	 * @param userId the user id
	 * @return the list
	 */
	public List<CityDTO> saveCity(CityRequestModel cityData, String userId);
	
	/**
	 * Save country.
	 *
	 * @param countryData the country data
	 * @param userId the user id
	 * @return the list
	 */
	public List<CountryDTO> saveCountry(CountryRequestModel countryData, String userId);
	
	/**
	 * Save continent.
	 *
	 * @param continentData the continent data
	 * @param userId the user id
	 * @return the list
	 */
	public List<ContinentDTO> saveContinent(ContinentRequestModel continentData, String userId);
	
	/**
	 * Delete city.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the city DTO
	 */
	public CityDTO deleteCity(String uniqueId, String userId);
	
	/**
	 * Delete country.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the list
	 */
	public List<CityDTO> deleteCountry (String uniqueId, String userId);
	
	/**
	 * Delete continent.
	 *
	 * @param uniqueId the unique id
	 * @param userId the user id
	 * @return the list
	 */
	public List<CityDTO> deleteContinent (String uniqueId, String userId);
	
	
	/**
	 * Search city.
	 *
	 * @param name the name
	 * @param publicUserId the public user id
	 * @return the list
	 */
	public List<CityDTO> searchCity(String name, String publicUserId);
	
	/**
	 * Search continent.
	 *
	 * @param name the name
	 * @param userId the user id
	 * @return the list
	 */
	public List<ContinentDTO> searchContinent(String name, String userId);	
	
	/**
	 * Search country.
	 *
	 * @param name the name
	 * @param userId the user id
	 * @return the list
	 */
	public List<CountryDTO> searchCountry(String name, String userId);
	
	/**
	 * Search city by country.
	 *
	 * @param name the name
	 * @param countryId the country id
	 * @param userId the user id
	 * @return the list
	 */
	public List<CityDTO> searchCityByCountry(String name, String countryId, String userId);
	
	/**
	 * Search city by continent.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @param userId the user id
	 * @return the list
	 */
	public List<CityDTO> searchCityByContinent(String name, String continentId, String userId);
	
	/**
	 * Search country by continent.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @param userId the user id
	 * @return the list
	 */
	public List<CountryDTO> searchCountryByContinent(String name, String continentId, String userId);


	/**
	 * Loads data from resource data.txt file.
	 *
	 * @param maxCount the max count
	 * @return the int
	 */
	public int loadFromResources(int maxCount);
	
}
