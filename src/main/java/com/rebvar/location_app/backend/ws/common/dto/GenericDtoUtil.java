package com.rebvar.location_app.backend.ws.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.rebvar.location_app.backend.ws.location_app.model.response.CityResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.ContinentResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CountryResponseModel;

public class GenericDtoUtil<T> {
	
	public static List dtoToResponseModelList(List<Object> dtos)
	{
		
		Class<? extends Object> type = dtos.get(0).getClass();
		
		List retList = null;
		 
		if (type == CityDTO.class)
		{
			retList = new ArrayList<CityDTO>();
		}
		else if (type == CountryDTO.class)
		{
			retList = new ArrayList<CountryDTO>();
		} 
		
		else if (type == ContinentDTO.class)
		{
			retList = new ArrayList<ContinentDTO>();
		}
		
		else
		{
			throw new RuntimeException("Invalid type specified..");
		}
		
		
		ModelMapper mapper = new ModelMapper();
		
		for (Object dto : dtos)
		{
			if (dto!=null)
			{
								
				if (type == CityDTO.class)
				{					
					CityDTO item = (CityDTO) dto;
					CityResponseModel ret;
					ret = mapper.map(item, CityResponseModel.class);
					CountryDTO countryDto=item.getCountry();
					ret.setContinentId(countryDto.getContinent().getUniqueId());
					ret.setCountryId(countryDto.getUniqueId());
					ret.setContinentName(countryDto.getContinent().getName());
					ret.setCountryName(countryDto.getName());
					retList.add(ret);
				}
				else if (type == CountryDTO.class)
				{
					CountryDTO item = (CountryDTO) dto;
					CountryResponseModel ret;
					ret = mapper.map(item, CountryResponseModel.class);
					
					ContinentDTO continentDto=item.getContinent();
					ret.setContinentId(continentDto.getUniqueId());
					ret.setContinentName(continentDto.getName());
					retList.add(ret);
				}
				else if (type == ContinentDTO.class)
				{
					ContinentDTO item = (ContinentDTO) dto;
					ContinentResponseModel ret;
					ret = mapper.map(item, ContinentResponseModel.class);					
					retList.add(ret);
				}
				
				else
				{
					throw new RuntimeException("Invalid Argument Types specified...");
				}
			}
		}
		return retList;
	}
	
	public static List dtoToResponseModelList(Object wdto)
	{
		List wdtoList = new ArrayList();
		if (wdto!=null)
			wdtoList.add(wdto);
		return dtoToResponseModelList(wdtoList);
	}
	
	
	
	
}