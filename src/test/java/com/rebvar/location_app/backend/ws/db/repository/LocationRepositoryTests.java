package com.rebvar.location_app.backend.ws.db.repository;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.rebvar.location_app.backend.ws.db.entity.CityEntity;
import com.rebvar.location_app.backend.ws.db.entity.ContinentEntity;
import com.rebvar.location_app.backend.ws.db.entity.CountryEntity;
import com.rebvar.location_app.backend.ws.security.SecurityUtils;
import com.rebvar.location_app.backend.ws.service.Impl.LocationServiceImpl;

/**
 * The Class LocationRepositoryTests.
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LocationRepositoryTests {

	
	/** The city repository. */
	@Autowired
	CityRepository cityRepository;

	/** The country repository. */
	@Autowired
	CountryRepository countryRepository;

	/** The continent repository. */
	@Autowired
	ContinentRepository continentRepository;

	/** The sutils. */
	@Autowired 
	SecurityUtils sutils;
	
	/** The location service. */
	@Autowired
	LocationServiceImpl locationService;
	
	/** The data created. */
	static boolean dataCreated = false;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		createRecords();
	}

	

		
	/**
	 * Test location country.
	 */
	@Test
	final void testLocationCountry() {
		
		CityEntity oulu = cityRepository.findByUniqueId("oulu");
		assertTrue(oulu.getName().compareTo("Oulu")==0);
		assertTrue(oulu.getCountry().getName().compareTo("Finland")==0);
		assertTrue(oulu.getCountry().getName().compareTo("Japan")!=0);
	}
	
	
	/**
	 * Test delete city.
	 */
	@Test
	final void testDeleteCity() {
		
		CityEntity oulu = cityRepository.findByUniqueId("oulu");
		assertNotNull(oulu);
		
		assertDoesNotThrow(()->{
			
			cityRepository.delete(oulu);
		});
		
	}
	
	
	/**
	 * Test delete country.
	 */
	@Test
	final void testDeleteCountry() {
		
		CountryEntity finland = countryRepository.findByUniqueId("finland");
		assertNotNull(finland);
		
		Iterable<CityEntity> citiesIt = cityRepository.findAll();
		List<CityEntity> cities = new ArrayList<CityEntity>();
		for (CityEntity city: citiesIt)
		{
			if (city.getCountry().getId() == finland.getId())
			cities.add(city);
		}
		
		assertNotNull(cities);		
		
		assertTrue(cities.size()==3);
		
		ContinentEntity europe = finland.getContinent();
		assertNotNull(europe);
		
		assertDoesNotThrow(()->{
			countryRepository.deleteById(finland.getId());
		});
		
		
		CityEntity oulu = cityRepository.findByUniqueId("oulu");
		assertNull(oulu);
		for (CityEntity city : cities)
		{
			CityEntity entity = cityRepository.findByUniqueId(city.getUniqueId());
			assertNull(entity);
		}
		
		List<CountryEntity> finlandList = countryRepository.findCountriesByPartialNameAndContinentId("finland", europe.getId());
		assertFalse(finlandList.size()>0);
		
		
		
	}
	
	
	/**
	 * Test insert duplicate location.
	 */
	@Test
	void testInsertDuplicateLocation() {
		
		CityEntity oulu = cityRepository.findByUniqueId("oulu");
		assertNotNull(oulu);
		CountryEntity finland = oulu.getCountry();
		assertNotNull(finland);
		ContinentEntity europe = finland.getContinent();
		assertNotNull(europe);
		
		assertThrows(Exception.class, ()->
		{
			CityEntity oulu2 = new CityEntity();
			oulu2.setUniqueId("oulu");
			oulu2.setName("Oulu");
			oulu2.setCountry(oulu.getCountry());
			cityRepository.save(oulu2);
		});
		
		
		assertThrows(Exception.class, ()->
			{ 
				CountryEntity entity = new CountryEntity();	
				entity.setName(finland.getName());
				entity.setUniqueId("AnyString");
				entity.setContinent(finland.getContinent());
				countryRepository.save(entity);
			});
		
		assertThrows(Exception.class, ()->
		{ 
			ContinentEntity entity = new ContinentEntity();
			entity.setName(europe.getName());
			entity.setUniqueId("AnyString");
			continentRepository.save(entity);
		});
		
		
		{
			CityEntity entity = new CityEntity();
			entity.setUniqueId("AnyString");
			entity.setName("Oulu");
			entity.setCountry(oulu.getCountry());
			assertDoesNotThrow(()->
			{
				cityRepository.save(entity);
			});
			
			assertNotNull(entity);
			assertTrue(entity.getId()>0);
			
			assertDoesNotThrow(()->
					{
						cityRepository.delete(entity);
					}
			);
		}
		
		assertThrows(Exception.class, ()->
			{ 
				CountryEntity entity = new CountryEntity();	
				entity.setName(finland.getName());
				entity.setUniqueId("AnyString");
				entity.setContinent(finland.getContinent());
				countryRepository.save(entity);
			});
		
		assertThrows(Exception.class, ()->
		{ 
			CountryEntity entity = new CountryEntity();	
			entity.setName("Sweden");
			entity.setUniqueId("finland");
			entity.setContinent(finland.getContinent());
			countryRepository.save(entity);
		});
	
		
		assertThrows(Exception.class, ()->
		{ 
			ContinentEntity entity = new ContinentEntity();
			entity.setName(europe.getName());
			entity.setUniqueId("AnyString");
			continentRepository.save(entity);
		});
		
		
		//Table is not case sensitive
		assertDoesNotThrow(()->
		{ 
			ContinentEntity entity = new ContinentEntity();
			entity.setName(europe.getName().toLowerCase());
			entity.setUniqueId("AnyString");
			continentRepository.save(entity);
		});	
		
	}
	
	
	
	
	
	/**
	 * Creates the records.
	 */
	private void createRecords() {
		
		cityRepository.deleteAll();
		countryRepository.deleteAll();
		continentRepository.deleteAll();
		
		ContinentEntity asia = new ContinentEntity();
		asia.setName("Asia");
		asia.setUniqueId("asia");
		
		
		
		CountryEntity japan = new CountryEntity();
		japan.setName("Japan");
		japan.setContinent(asia);
		japan.setUniqueId("japan");
		asia.AddCountry(japan);
		
		CityEntity tokyo = new CityEntity();
		tokyo.setName("Tokyo");
		tokyo.setCountry(japan);
		tokyo.setUniqueId("tokyo");
		japan.AddCity(tokyo);
		
		continentRepository.save(asia);
		
		ContinentEntity europe = new ContinentEntity();
		europe.setName("Europe");
		europe.setUniqueId("europe");
		
		CountryEntity finland = new CountryEntity();
		finland.setName("Finland");
		finland.setContinent(europe);
		finland.setUniqueId("finland");
		europe.AddCountry(finland);
		
		
		CityEntity oulu = new CityEntity();
		oulu.setName("Oulu");
		oulu.setCountry(finland);
		oulu.setUniqueId("oulu");
		finland.AddCity(oulu);
		
		CityEntity helsinki = new CityEntity();
		helsinki.setName("Helsinki");
		helsinki.setCountry(finland);
		helsinki.setUniqueId("helsinki");
		finland.AddCity(helsinki);

		CityEntity tampere = new CityEntity();
		tampere.setName("Tampere");
		tampere.setUniqueId("tampere");
		tampere.setCountry(finland);
		finland.AddCity(tampere);
		
		continentRepository.save(europe);
		
	}
	
	/**
	 * Test bulk load resource data.
	 */
	@Test
	@Transactional
	public void testBulkLoadResourceData()
	{
		cityRepository.deleteAll();
		countryRepository.deleteAll();
		continentRepository.deleteAll();
		int count = locationService.loadFromResources(100);
		assertTrue(count>0);
		Iterable<CityEntity> cities = cityRepository.findAll();
		int count2 = 0;
		for (CityEntity city : cities)
		{
			count2+=1;
		}
		assertTrue(count2>0);
		assertTrue(count == count2);
	}
	
}
