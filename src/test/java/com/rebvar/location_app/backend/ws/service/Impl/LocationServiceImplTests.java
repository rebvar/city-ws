package com.rebvar.location_app.backend.ws.service.Impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rebvar.location_app.backend.ws.db.entity.CityEntity;
import com.rebvar.location_app.backend.ws.db.entity.ContinentEntity;
import com.rebvar.location_app.backend.ws.db.entity.CountryEntity;
import com.rebvar.location_app.backend.ws.db.repository.CityRepository;
import com.rebvar.location_app.backend.ws.db.repository.ContinentRepository;
import com.rebvar.location_app.backend.ws.db.repository.CountryRepository;
import com.rebvar.location_app.backend.ws.db.repository.UserRepository;
import com.rebvar.location_app.backend.ws.location_app.model.request.CityRequestModel;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.common.dto.CityDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class LocationServiceImplTest {

	@InjectMocks
	LocationServiceImpl locationService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	CityRepository cityRepository;
	
	@Mock
	CountryRepository countryRepository;
	
	@Mock 
	ContinentRepository continentRepository;
	
	@Mock
	SecurityUtils sutils;

	
	
	ContinentEntity europe;
	CountryEntity finland;
	CityEntity oulu, tampere, helsinki;

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	    europe = new ContinentEntity();
	    europe.setName("europe");
	    europe.setUniqueId("europe");
	    europe.setId(1L);
	    
	    finland = new CountryEntity();
	    finland.setName("finland");
	    finland.setUniqueId("finland");
	    finland.setContinent(europe);
	    finland.setId(1L);
	    europe.AddCountry(finland);
	    
		oulu = new CityEntity();
		oulu.setName("oulu");
		oulu.setCountry(finland);
		oulu.setUniqueId("oulu");
		oulu.setId(1L);
		finland.AddCity(oulu);
		
		tampere = new CityEntity();
		tampere.setName("tampere");
		tampere.setCountry(finland);
		tampere.setUniqueId("tampere");
		tampere.setId(2L);
		finland.AddCity(tampere);
		
		helsinki = new CityEntity();
		helsinki.setName("helsinki");
		helsinki.setCountry(finland);
		helsinki.setUniqueId("helsinki");
		helsinki.setId(3L);
		finland.AddCity(helsinki);
		
	}

	@Test
	final void testGetCity() {
		when(cityRepository.findByUniqueId(anyString())).thenReturn(oulu);
		CityDTO ouluResp =  locationService.getCity("oulu", "");
		assertNotNull(ouluResp);
		assertEquals(ouluResp.getName(), oulu.getName());
		assertEquals(ouluResp.getCountry().getUniqueId(), finland.getUniqueId());
	}
	
	@Test
	final void testSearchCity() {
		when(cityRepository.findCitiesByPattern(anyString())).thenReturn(new ArrayList<CityEntity>(Arrays.asList(tampere, helsinki)));
		List<CityDTO> resp =  locationService.searchCity("e", "");
		assertNotNull(resp);
		assertTrue(resp.size()==2);
		CityDTO tampereResp = resp.get(0);
		assertNotNull(tampereResp);
		assertEquals(tampereResp.getName(), tampere.getName());
		assertEquals(tampereResp.getCountry().getUniqueId(), finland.getUniqueId());
		assertNotNull(resp.stream().anyMatch(city->city.getUniqueId().compareTo(helsinki.getUniqueId())==0));
	}
	
	
	
	@Test
	final void testSaveCity() {
		
		when(continentRepository.save(any())).thenReturn(europe);
		when(countryRepository.save(any())).thenReturn(finland);
		when(countryRepository.findByName(any())).thenReturn(finland);
		when(countryRepository.findByUniqueId(any())).thenReturn(finland);
		when(continentRepository.findByName(any())).thenReturn(europe);
		when(continentRepository.findByUniqueId(any())).thenReturn(europe);
		
		CityRequestModel city = null;
		List<CityDTO> resp =  locationService.saveCity(city, "");
		
		assertNotNull(resp);
		assertTrue(resp.size()==0);
		
		assertThrows(Exception.class,()->
		{
			CityRequestModel city2 = new CityRequestModel();
			locationService.saveCity(city2, "");
			
		});
		
		
		CityEntity pori = new CityEntity();
		pori.setCountry(finland);
		pori.setName("pori");
		pori.setId(4L);
		pori.setUniqueId("pori");
		when(cityRepository.save(any())).thenReturn(pori);
		
		city = new CityRequestModel();
		city.setName("Pori");
		city.setCountryName("Finland");
		resp =  locationService.saveCity(city, "");
		assertNotNull(resp);
		assertEquals(resp.size(), 1);
		CityDTO respPori = resp.get(0);
		assertEquals(respPori.getName(), pori.getName());
		assertEquals(respPori.getCountry().getUniqueId(), finland.getUniqueId());
		
		city.setCountryName(null);
		city.setCountryUniqueId(finland.getUniqueId());
		
		resp =  locationService.saveCity(city, "");
		assertNotNull(resp);
		assertEquals(resp.size(), 1);
		respPori = resp.get(0);
		assertEquals(respPori.getName(), pori.getName());
		assertEquals(respPori.getCountry().getUniqueId(), finland.getUniqueId());
		
		//Ignore country name if country Id is specified
		city.setCountryName("Sweden");
		city.setCountryUniqueId(finland.getUniqueId());
		
		resp =  locationService.saveCity(city, "");
		assertNotNull(resp);
		assertEquals(resp.size(), 1);
		respPori = resp.get(0);
		assertEquals(respPori.getName(), pori.getName());
		assertEquals(respPori.getCountry().getUniqueId(), finland.getUniqueId());
	}
}
