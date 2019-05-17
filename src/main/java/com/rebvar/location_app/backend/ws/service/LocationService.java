package com.rebvar.location_app.backend.ws.service;

import java.util.List;

import com.rebvar.location_app.backend.ws.common.dto.CityDTO;
import com.rebvar.location_app.backend.ws.common.dto.ContinentDTO;
import com.rebvar.location_app.backend.ws.common.dto.CountryDTO;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.ContinentRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.CountryRequestModel;

/**
 * @author sehossei
 * Weather Service. In charge of search, storage and retrieval of the location weather processes.
 */
/**
 * @author sehossei
 *
 */
public interface LocationService {

	public CityDTO getCity(String uniqueId, String userId);
	public CountryDTO getCountry(String uniqueId, String userId);
	public ContinentDTO getContinent(String uniqueId, String userId);	
	
	public CityDTO addToFavorites(String uniqueId, String userId);
	public CityDTO deleteFromFavorites(String uniqueId, String userId);
	
	
	public List<CityDTO> saveCity(CityRequestModel cityData, String userId);
	public List<CountryDTO> saveCountry(CountryRequestModel countryData, String userId);
	public List<ContinentDTO> saveContinent(ContinentRequestModel continentData, String userId);
	
	public CityDTO deleteCity(String uniqueId, String userId);
	public List<CityDTO> deleteCountry (String uniqueId, String userId);
	public List<CityDTO> deleteContinent (String uniqueId, String userId);
	
	
	public List<CityDTO> searchCity(String name, String publicUserId);
	public List<ContinentDTO> searchContinent(String name, String userId);	
	public List<CountryDTO> searchCountry(String name, String userId);
	public List<CityDTO> searchCityByCountry(String name, String countryId, String userId);
	public List<CityDTO> searchCityByContinent(String name, String continentId, String userId);
	public List<CountryDTO> searchCountryByContinent(String name, String continentId, String userId);


	public int bulkLoad(int maxCount);
	//public List<CityDTO> getAllFavoriteCities(String userId);
	
	//public List<LocationWeatherDTO> getAllCitySearches(String userId);
}
