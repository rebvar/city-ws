package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.CityEntity;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {
	CityEntity findByUniqueId(String uniqueId);
	
	@Query(value="select * from city where name LIKE %:pattern%",nativeQuery=true)
	List<CityEntity> findCitiesByPattern(@Param("pattern") String pattern);
	
	
	@Query(value="select * from city where name = :name and country_id = :countryId",nativeQuery=true)
	List<CityEntity> findCitiesByExactNameAndCountryId(@Param("name") String name, @Param("countryId") long countryId);
	
	@Query(value="select * from city where name LIKE %:name% and country_id = :countryId", nativeQuery=true)
	List<CityEntity> findCitiesByPartialNameAndCountryId(@Param("name") String name, @Param("countryId") long countryId);
	
	
	@Query(value="select * from city where name LIKE %:name% and country_id in (select id from country where continent_id = :continentId)", nativeQuery=true)
	List<CityEntity> findCitiesByPartialNameAndContinentId(@Param("name") String name, @Param("continentId") long continentId);
	
}
