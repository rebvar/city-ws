package com.rebvar.location_app.backend.ws.location_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebvar.location_app.backend.ws.AppConstants;
import com.rebvar.location_app.backend.ws.common.dto.CityDTO;
import com.rebvar.location_app.backend.ws.common.dto.ContinentDTO;
import com.rebvar.location_app.backend.ws.common.dto.CountryDTO;
import com.rebvar.location_app.backend.ws.common.dto.DtoUtil;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.ContinentRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.request.CountryRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CityResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.ContinentResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CountryResponseModel;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.service.LocationService;

/**
 * The Class LocationController.
 */
@RestController
@RequestMapping("/map")
public class LocationController {

	/** The location service. */
	@Autowired
	LocationService locationService;
	
	/** The security utility module. */
	@Autowired
	SecurityUtils sutils;
	
	
	/**
	 * Gets the city by id.
	 *
	 * @param cityId the city id
	 * @param Auth_Token the auth token
	 * @return search by city: Format : /map/city/id of the city
	 */
	@GetMapping(path = "/city/{cityId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> getCity(@PathVariable String cityId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		CityDTO cityDto = locationService.getCity(cityId, userId);
		//List<CityResponseModel> retCityList = new GenericDtoConvertUtil<CityResponseModel>().dtoToResponseModelList(cityDto);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cityDto);
		return retCityList;
	}
	
	/**
	 * Delete city.
	 *
	 * @param cId the id of the city
	 * @param Auth_Token the auth token
	 * @return a list containing a deleted city or an empty list if the city does not exist.
	 */
	@DeleteMapping(path = "/city/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteCity(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		CityDTO cityDto = locationService.deleteCity(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cityDto);		
		return retCityList;
	}
	
	
	/**
	 * Delete country.
	 *
	 * @param cId the id of the country
	 * @param Auth_Token the auth token
	 * @return a list containing the deleted cities for that country or an empty list if the country does not exist.
	 */
	@DeleteMapping(path = "/country/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteCountry(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cDtos = locationService.deleteCountry(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cDtos);		
		return retCityList;
	}
	
	
	/**
	 * Delete continent.
	 *
	 * @param cId the id of the continent
	 * @param Auth_Token the auth token
	 * @return a list containing the deleted cities in that continent or an empty list if the continent does not exist.
	 */
	@DeleteMapping(path = "/continent/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteContinent(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cDtos = locationService.deleteContinent(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cDtos);		
		return retCityList;
	}
	
	/**
	 * Adds the city.
	 *
	 * @param cityData the city data from request body
	 * @param Auth_Token the auth token
	 * @return a list containing the added city if the city does not exist, otherwise a list that contains the existing city information.
	 */
	@PostMapping(path = "/city", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> addCity(@RequestBody CityRequestModel cityData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cityDtos = locationService.saveCity(cityData, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cityDtos);		
		return retCityList;
	}
	
	
	/**
	 * Adds the country.
	 *
	 * @param countryData the country data
	 * @param Auth_Token the auth token
	 * @return a list containing the added county if the county does not exist, otherwise a list that contains the existing country information.
	 */
	@PostMapping(path = "/country", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> addCountry(@RequestBody CountryRequestModel countryData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> countryDtos = locationService.saveCountry(countryData, userId);
		List<CountryResponseModel> retList = DtoUtil.dtoToCountryResponseModelList(countryDtos);		
		return retList;
	}
	
	/**
	 * Adds the continent.
	 *
	 * @param continentData the continent data
	 * @param Auth_Token the auth token
	 * @return a list containing the added continent if the continent does not exist, otherwise a list that contains the existing continent information.
	 */
	@PostMapping(path = "/continent", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ContinentResponseModel> addContinent(@RequestBody ContinentRequestModel continentData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<ContinentDTO> continentDtos = locationService.saveContinent(continentData, userId);
		List<ContinentResponseModel> retList = DtoUtil.dtoToContinentResponseModelList(continentDtos);		
		return retList;
	}
	
	/**
	 * Search cities.
	 *
	 * @param name the partial name of the city to look for 
	 * @param Auth_Token the auth token
	 * @return a list containing the cities having the specified value for parameter name in their names
	 */
	@GetMapping(path = {"/search/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCities(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCity(name, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	/**
	 * Search countries.
	 *
	 * @param name the partial name of the countries
	 * @param Auth_Token the auth token
	 * @return a list containing the countries having the specified value for parameter name in their names
	 */
	@GetMapping(path = {"/search/country/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> searchCountries(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> dtos = locationService.searchCountry(name, userId);
		
		return DtoUtil.dtoToCountryResponseModelList(dtos);
	}
	
	
	/**
	 * Search continents.
	 *
	 * @param name the partial name of continents
	 * @param Auth_Token the auth token
	 * @return a list containing the continents having the specified value for parameter name in their names
	 */
	@GetMapping(path = {"/search/continent/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ContinentResponseModel> searchContinents(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<ContinentDTO> dtos = locationService.searchContinent(name, userId);
		return DtoUtil.dtoToContinentResponseModelList(dtos);
	}
	
	/**
	 * Search cities in country.
	 *
	 * @param countryId the country id
	 * @param name the partial city name
	 * @param Auth_Token the auth token
	 * @return a list containing the cities having the specified value for parameter name in their names in country with id countryId
	 */
	@GetMapping(path = {"/search/country/{countryId}/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCitiesInCountry(@PathVariable String countryId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{	
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCityByCountry(name, countryId, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	
	/**
	 * Search cities in continent.
	 *
	 * @param continentId the continent id
	 * @param name the partial city name
	 * @param Auth_Token the auth token
	 * @return a list containing the cities having the specified value for parameter name in their names in continent with id continentId
	 */
	@GetMapping(path = {"/search/continent/{continentId}/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCitiesInContinent(@PathVariable String continentId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCityByContinent(name, continentId, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	/**
	 * Search countries in continent.
	 *
	 * @param continentId the continent id
	 * @param name the partial country name
	 * @param Auth_Token the auth token
	 * @return a list containing the countries having the specified value for parameter name in their names in continent with id continentId
	 */
	@GetMapping(path = {"/search/continent/{continentId}/country/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> searchCountriesInContinent(@PathVariable String continentId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> countriesDto = locationService.searchCountryByContinent(name, continentId, userId);
		return DtoUtil.dtoToCountryResponseModelList(countriesDto);
	}
	
	/**
	 * Bulk load.
	 *
	 * @param count the number of rows to read from data.txt and add to the database.
	 * @param Auth_Token the auth token
	 * @return the number of rows that either inserted, or existed before if there are city-county-continents that were inserted before.
	 */
	@PostMapping(path = {"/admin/bulk-load/{count}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public int bulkLoad(@PathVariable int count, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		int countRet = locationService.loadFromResources(count);
		return countRet;
	}
	
}
