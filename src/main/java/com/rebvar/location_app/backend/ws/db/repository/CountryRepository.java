package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.CountryEntity;

/**
 * The Interface CountryRepository.
 */
@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
	
	/**
	 * Find by unique id.
	 *
	 * @param uniqueId the unique id
	 * @return the country entity
	 */
	CountryEntity findByUniqueId(String uniqueId);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the country entity
	 */
	CountryEntity findByName(String name);	

	/**
	 * Find countries by pattern.
	 *
	 * @param pattern the pattern
	 * @return the list
	 */
	@Query(value="select * from country where name LIKE %:pattern%",nativeQuery=true)
	List<CountryEntity> findCountriesByPattern(@Param("pattern") String pattern);
	
	/**
	 * Find countries by partial name and continent id.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @return the list
	 */
	@Query(value="select * from country where name LIKE %:name% and continent_id = :continentId", nativeQuery=true)
	List<CountryEntity> findCountriesByPartialNameAndContinentId(@Param("name") String name, @Param("continentId") long continentId);
}
