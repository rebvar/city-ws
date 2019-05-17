package com.rebvar.location_app.backend.ws.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.rebvar.location_app.backend.ws.location_app.model.response.CityResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.ContinentResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CountryResponseModel;

public class GenericDtoConvertUtil<T> {
	
	public List<T> dtoToResponseModelList(List<Object> dtos)
	{
		List<T> retList = new ArrayList<T>();
		
		if (dtos.size()<=0)
			return retList;
		Class<? extends Object> type = dtos.get(0).getClass();
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
					retList.add((T)ret);
				}
				else if (type == CountryDTO.class)
				{
					CountryDTO item = (CountryDTO) dto;
					CountryResponseModel ret;
					ret = mapper.map(item, CountryResponseModel.class);
					
					ContinentDTO continentDto=item.getContinent();
					ret.setContinentId(continentDto.getUniqueId());
					ret.setContinentName(continentDto.getName());
					retList.add((T)ret);
				}
				else if (type == ContinentDTO.class)
				{
					ContinentDTO item = (ContinentDTO) dto;
					ContinentResponseModel ret;
					ret = mapper.map(item, ContinentResponseModel.class);					
					retList.add((T)ret);
				}
				else
				{
					throw new RuntimeException("Invalid Argument Types specified...");
				}
			}
		}
		return retList;
	}
	
	
	public List<T> dtoToResponseModel(Object wdto)
	{
		List<Object> wdtoList = new ArrayList<Object>();
		if (wdto!=null)
			wdtoList.add(wdto);
		return dtoToResponseModelList(wdtoList);
	}
}
