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

@RestController
@RequestMapping("/map")
public class LocationController {

	@Autowired
	LocationService locationService;
	
	@Autowired
	SecurityUtils sutils;
	
	
	/**
	 * @param city
	 * @param Auth_Token
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
	
	@DeleteMapping(path = "/city/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteCity(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		CityDTO cityDto = locationService.deleteCity(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cityDto);		
		return retCityList;
	}
	
	
	@DeleteMapping(path = "/country/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteCountry(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cDtos = locationService.deleteCountry(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cDtos);		
		return retCityList;
	}
	
	
	@DeleteMapping(path = "/continent/{cId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> deleteContinent(@PathVariable String cId, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cDtos = locationService.deleteContinent(cId, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cDtos);		
		return retCityList;
	}
	
	@PostMapping(path = "/city", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> addCity(@RequestBody CityRequestModel cityData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> cityDtos = locationService.saveCity(cityData, userId);
		List<CityResponseModel> retCityList = DtoUtil.dtoToCityResponseModelList(cityDtos);		
		return retCityList;
	}
	
	
	@PostMapping(path = "/country", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> addCountry(@RequestBody CountryRequestModel countryData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> countryDtos = locationService.saveCountry(countryData, userId);
		List<CountryResponseModel> retList = DtoUtil.dtoToCountryResponseModelList(countryDtos);		
		return retList;
	}
	
	@PostMapping(path = "/continent", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ContinentResponseModel> addContinent(@RequestBody ContinentRequestModel continentData,@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<ContinentDTO> continentDtos = locationService.saveContinent(continentData, userId);
		List<ContinentResponseModel> retList = DtoUtil.dtoToContinentResponseModelList(continentDtos);		
		return retList;
	}
	
	@GetMapping(path = {"/search/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCities(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCity(name, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	@GetMapping(path = {"/search/country/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> searchCountries(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> dtos = locationService.searchCountry(name, userId);
		
		return DtoUtil.dtoToCountryResponseModelList(dtos);
	}
	
	
	@GetMapping(path = {"/search/continent/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ContinentResponseModel> searchContinents(@PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<ContinentDTO> dtos = locationService.searchContinent(name, userId);
		return DtoUtil.dtoToContinentResponseModelList(dtos);
	}
	
	@GetMapping(path = {"/search/country/{countryId}/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCitiesInCountry(@PathVariable String countryId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{	
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCityByCountry(name, countryId, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	
	@GetMapping(path = {"/search/continent/{continentId}/city/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CityResponseModel> searchCitiesInContinent(@PathVariable String continentId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{		
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CityDTO> citiesDto = locationService.searchCityByContinent(name, continentId, userId);
		return DtoUtil.dtoToCityResponseModelList(citiesDto);
	}
	
	@GetMapping(path = {"/search/continent/{continentId}/country/{name}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CountryResponseModel> searchCountriesInContinent(@PathVariable String continentId, @PathVariable String name, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		List<CountryDTO> countriesDto = locationService.searchCountryByContinent(name, continentId, userId);
		return DtoUtil.dtoToCountryResponseModelList(countriesDto);
	}
	
	@PostMapping(path = {"/admin/bulk-load/{count}"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public int bulkLoad(@PathVariable int count, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
	{
		String userId = sutils.getUserIdFromToken(Auth_Token);
		int countRet = locationService.bulkLoad(count);
		return countRet;
	}
	
//	/**
//	 * @param fav
//	 * @param Auth_Token
//	 * @return Adds an item to the favorites. Due to the three design options for this behaviours, a POST method is used. 
//	 * Can be PUT mapping as well. Returns an OperationResult value.  
//	 */
//	@PostMapping(path = {"/favorites"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public LocationWeatherResponseModel AddToFavorites(@RequestBody UserFavoritesRequestModel fav, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
//	{
//		String userId = sutils.getUserIdFromToken(Auth_Token);
//		if (userId.isEmpty())
//			throw new RuntimeException("Invalid authorisation token... No user info has been specified..");
//		return new ModelMapper().map(weatherService.addToFavorites(fav.getUniqueId(), userId), LocationWeatherResponseModel.class);
//	}
//	
//	/**
//	 * @param Auth_Token
//	 * @return All the searches by a logged in user. Throws exception for invalid auth_token values.
//	 */
//	@GetMapping(path = "/searches", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public List<LocationWeatherResponseModel> getUserSearches(@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
//	{
//		String userId = sutils.getUserIdFromToken(Auth_Token);
//		if (userId.isEmpty())
//			throw new RuntimeException("Invalid authorisation token... No user info has been specified..");
//		
//		List<LocationWeatherDTO> wdtos = weatherService.getAllWeatherSearches(userId);
//		List<LocationWeatherResponseModel> ret = new ArrayList<LocationWeatherResponseModel>();
//		ModelMapper mapper = new ModelMapper();
//		wdtos.forEach(wdto->ret.add(mapper.map(wdto, LocationWeatherResponseModel.class)));
//		return ret;
//	}
//	
//	
//	/**
//	 * @param Auth_Token
//	 * @return All the favorites for a logged in user. Throws exception for invalid auth_token values.
//	 */
//	@GetMapping(path = {"/favorites"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public List<LocationWeatherResponseModel> getAllFavorites(@RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
//	{
//		String userId = sutils.getUserIdFromToken(Auth_Token);
//		if (userId.isEmpty())
//			throw new RuntimeException("Invalid authorisation token... No user info has been specified..");
//	    List<LocationWeatherDTO> wdtos = weatherService.getAllFavorites(userId);
//	    ModelMapper mapper = new ModelMapper();
//	    
//	    List<LocationWeatherResponseModel> retRest = new ArrayList<LocationWeatherResponseModel>();
//	    wdtos.forEach(wdto->retRest.add(mapper.map(wdto, LocationWeatherResponseModel.class)));
//	    return retRest;
//	}
//	
//	
//	/**
//	 * @param fav
//	 * @param Auth_Token
//	 * @return Removes an item from the user favorites. 
//	 */
//	@DeleteMapping(path = {"/favorites"}, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public LocationWeatherResponseModel deleteFromFavorites(@RequestBody UserFavoritesRequestModel fav, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
//	{
//		String userId = sutils.getUserIdFromToken(Auth_Token);
//		if (userId.isEmpty())
//			throw new RuntimeException("Invalid authorisation token... No user info has been specified..");
//	    LocationWeatherDTO res = weatherService.deleteFromFavorites(fav.getUniqueId(), userId);
//	    return new ModelMapper().map(res, LocationWeatherResponseModel.class);
//	}
//	
//	
//	/**
//	 * @param id
//	 * @param Auth_Token
//	 * @return Returns a weather search from the two tables. If the user is valid and logged in, from LocationWeather. 
//	 * if not from the Anonymous table. Accepts a public unique Id.
//	 */
//	@GetMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public LocationWeatherResponseModel getSearchedWeather(@PathVariable String id, @RequestHeader(name = AppConstants.HEADER_STRING, defaultValue = AppConstants.INVALID_AUTH_DEFAULT_VALUE) String Auth_Token)
//	{
//		String userId = sutils.getUserIdFromToken(Auth_Token);
//		LocationWeatherDTO locationWeather = weatherService.getSavedWeather(id, userId);
//		return dtoToResponseModel(locationWeather);
//	}
	
}
