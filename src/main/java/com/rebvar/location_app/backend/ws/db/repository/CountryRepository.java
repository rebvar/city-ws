package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.CountryEntity;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
	CountryEntity findByUniqueId(String uniqueId);
	CountryEntity findByName(String name);	

	@Query(value="select * from country where name LIKE %:pattern%",nativeQuery=true)
	List<CountryEntity> findCountriesByPattern(@Param("pattern") String pattern);
	
	@Query(value="select * from country where name LIKE %:name% and continent_id = :continentId", nativeQuery=true)
	List<CountryEntity> findCountriesByPartialNameAndContinentId(@Param("name") String name, @Param("continentId") long continentId);
}
