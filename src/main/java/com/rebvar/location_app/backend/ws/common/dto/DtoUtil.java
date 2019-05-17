package com.rebvar.location_app.backend.ws.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.rebvar.location_app.backend.ws.location_app.model.response.CityResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.ContinentResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CountryResponseModel;

/**
 * The Class DtoUtil.
 */
public class DtoUtil {

	/**
	 * Dto to city response model list.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	public static List<CityResponseModel> dtoToCityResponseModelList(List<CityDTO> dtos)
	{
		List<CityResponseModel> retList = new ArrayList<CityResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for (CityDTO item : dtos)
		{
			if (item!=null)
			{
				CityResponseModel ret;				
				ret = mapper.map(item, CityResponseModel.class);
				CountryDTO countryDto=item.getCountry();
				ret.setContinentId(countryDto.getContinent().getUniqueId());
				ret.setCountryId(countryDto.getUniqueId());
				ret.setContinentName(countryDto.getContinent().getName());
				ret.setCountryName(countryDto.getName());
				
				retList.add(ret);
			}
		}
		return retList;
	}
	
	/**
	 * Dto to city response model list.
	 *
	 * @param wdto the wdto
	 * @return the list
	 */
	public static List<CityResponseModel> dtoToCityResponseModelList(CityDTO wdto)
	{
		List<CityDTO> dtos = new ArrayList<CityDTO>();
		if (wdto!=null)
			dtos.add(wdto);
		return dtoToCityResponseModelList(dtos);
	}
	
	
	/**
	 * Dto to country response model list.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	public static List<CountryResponseModel> dtoToCountryResponseModelList(List<CountryDTO> dtos)
	{
		List<CountryResponseModel> retList = new ArrayList<CountryResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for (CountryDTO item : dtos)
		{
			if (item!=null)
			{
				CountryResponseModel ret;				
				ret = mapper.map(item, CountryResponseModel.class);
				ContinentDTO continentDto=item.getContinent();
				ret.setContinentId(continentDto.getUniqueId());
				ret.setContinentName(continentDto.getName());
				retList.add(ret);
			}
		}
		return retList;
	}
	

	/**
	 * Dto to country response model list.
	 *
	 * @param wdto the wdto
	 * @return the list
	 */
	public static List<CountryResponseModel> dtoToCountryResponseModelList(CountryDTO wdto)
	{
		List<CountryDTO> dtos = new ArrayList<CountryDTO>();
		if (wdto!=null)
			dtos.add(wdto);
		return dtoToCountryResponseModelList(dtos);
	}
	
	/**
	 * Dto to continent response model list.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	public static List<ContinentResponseModel> dtoToContinentResponseModelList(List<ContinentDTO> dtos)
	{
		List<ContinentResponseModel> retList = new ArrayList<ContinentResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for (ContinentDTO item : dtos)
		{
			if (item!=null)
			{
				ContinentResponseModel ret;				
				ret = mapper.map(item, ContinentResponseModel.class);
				retList.add(ret);
			}
		}
		return retList;
	}
	

	/**
	 * Dto to continent response model list.
	 *
	 * @param wdto the wdto
	 * @return the list
	 */
	public static List<ContinentResponseModel> dtoToContinentResponseModelList(ContinentDTO wdto)
	{
		List<ContinentDTO> dtos = new ArrayList<ContinentDTO>();
		if (wdto!=null)
			dtos.add(wdto);
		return dtoToContinentResponseModelList(dtos);
	}	
}
