package com.rebvar.location_app.backend.ws.location_app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebvar.location_app.backend.ws.service.Impl.LocationServiceImpl;
import com.rebvar.location_app.backend.ws.service.Impl.UserServiceImpl;
import com.rebvar.location_app.backend.ws.common.dto.CityDTO;
import com.rebvar.location_app.backend.ws.common.dto.ContinentDTO;
import com.rebvar.location_app.backend.ws.common.dto.CountryDTO;

import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CityResponseModel;
import com.rebvar.location_app.backend.ws.location_app.model.response.CountryResponseModel;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class LocationControllerTest {

	@InjectMocks
	LocationController locationController;
	
	@Mock
	UserServiceImpl userService;
	
	@Mock
	LocationServiceImpl locationService;
	
	
	@Mock
	SecurityUtils sutils;
	
	
	ContinentDTO europe;
	CountryDTO finland;
	CityDTO oulu;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
	    europe = new ContinentDTO();
	    europe.setName("europe");
	    europe.setUniqueId("europe");
	    
	    finland = new CountryDTO();
	    finland.setName("Finland");
	    finland.setUniqueId("finland");
	    finland.setContinent(europe);
	    
		oulu = new CityDTO();
		oulu.setName("Oulu");
		oulu.setCountry(finland);
		oulu.setUniqueId("oulu");
		
	}

	@Test
	final void testGetCity() {
	    when(locationService.getCity(anyString(),anyString())).thenReturn(oulu);
	    when(sutils.getUserIdFromToken(anyString())).thenReturn("");
	    List<CityResponseModel> resp = locationController.getCity("oulu","");
	    assertNotNull(resp);
	    assertTrue (resp.size()==1);
	    CityResponseModel ouluResp = resp.get(0); 
	    assertEquals(ouluResp.getName(), oulu.getName());
	    assertEquals(ouluResp.getUniqueId(), oulu.getUniqueId());
	    assertEquals(ouluResp.getCountryId(),oulu.getCountry().getUniqueId());
	}
	
	@Test
	final void testSearchCity() {
		
	    when(locationService.searchCity(anyString(),anyString())).thenReturn(new ArrayList<CityDTO>(Arrays.asList(oulu)));
	    when(sutils.getUserIdFromToken(anyString())).thenReturn("");
	    List<CityResponseModel> resp = locationController.searchCities("lu","");
	    assertNotNull(resp);
	    assertTrue (resp.size()==1);
	    CityResponseModel ouluResp = resp.get(0); 
	    assertEquals(ouluResp.getName(), oulu.getName());
	    assertEquals(ouluResp.getUniqueId(), oulu.getUniqueId());
	    assertEquals(ouluResp.getCountryId(),oulu.getCountry().getUniqueId());
	}
	
	@Test
	final void testSearchCityByCountry() {
		
	    when(locationService.searchCityByCountry(anyString(),anyString(), anyString())).thenReturn(new ArrayList<CityDTO>(Arrays.asList(oulu)));
	    when(sutils.getUserIdFromToken(anyString())).thenReturn("");
	    List<CityResponseModel> resp = locationController.searchCitiesInCountry("finland","lu","");
	    assertNotNull(resp);
	    assertTrue (resp.size()==1);
	    CityResponseModel ouluResp = resp.get(0); 
	    assertEquals(ouluResp.getName(), oulu.getName());
	    assertEquals(ouluResp.getUniqueId(), oulu.getUniqueId());
	    assertEquals(ouluResp.getCountryId(),oulu.getCountry().getUniqueId());
	}

	@Test
	final void testSearchCountryByContinent() {
		
	    when(locationService.searchCountryByContinent(anyString(),anyString(), anyString())).thenReturn(new ArrayList<CountryDTO>(Arrays.asList(finland)));
	    when(sutils.getUserIdFromToken(anyString())).thenReturn("");
	    List<CountryResponseModel> resp = locationController.searchCountriesInContinent("europe","fi","");
	    assertNotNull(resp);
	    assertTrue (resp.size()==1);
	    CountryResponseModel finResp = resp.get(0); 
	    assertEquals(finResp.getName(), finland.getName());
	    assertEquals(finResp.getUniqueId(), finland.getUniqueId());
	    assertEquals(finResp.getContinentId(),finland.getContinent().getUniqueId());
	}
	
	@Test
	final void testSaveCity() {
		
	    when(locationService.saveCity(any(), anyString())).thenReturn(new ArrayList<CityDTO>(Arrays.asList(oulu)));
	    when(sutils.getUserIdFromToken(anyString())).thenReturn("");
	    List<CityResponseModel> resp = locationController.addCity(new CityRequestModel(),"");
	    assertNotNull(resp);
	    assertTrue (resp.size()==1);
	    CityResponseModel ouluResp = resp.get(0); 
	    assertEquals(ouluResp.getName(), oulu.getName());
	    assertEquals(ouluResp.getUniqueId(), oulu.getUniqueId());
	    assertEquals(ouluResp.getCountryId(),oulu.getCountry().getUniqueId());
	}
	
	
}
