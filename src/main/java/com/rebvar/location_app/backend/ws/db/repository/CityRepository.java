package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.CityEntity;

/**
 * The Interface CityRepository.
 */
@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {
	
	/**
	 * Find by unique id.
	 *
	 * @param uniqueId the unique id
	 * @return the city entity
	 */
	CityEntity findByUniqueId(String uniqueId);
	
	/**
	 * Find cities by pattern.
	 *
	 * @param pattern the pattern
	 * @return the list
	 */
	@Query(value="select * from city where name LIKE %:pattern%",nativeQuery=true)
	List<CityEntity> findCitiesByPattern(@Param("pattern") String pattern);
	
	
	/**
	 * Find cities by exact name and country id.
	 *
	 * @param name the name
	 * @param countryId the country id
	 * @return the list
	 */
	@Query(value="select * from city where name = :name and country_id = :countryId",nativeQuery=true)
	List<CityEntity> findCitiesByExactNameAndCountryId(@Param("name") String name, @Param("countryId") long countryId);
	
	/**
	 * Find cities by partial name and country id.
	 *
	 * @param name the name
	 * @param countryId the country id
	 * @return the list
	 */
	@Query(value="select * from city where name LIKE %:name% and country_id = :countryId", nativeQuery=true)
	List<CityEntity> findCitiesByPartialNameAndCountryId(@Param("name") String name, @Param("countryId") long countryId);
	
	
	/**
	 * Find cities by partial name and continent id.
	 *
	 * @param name the name
	 * @param continentId the continent id
	 * @return the list
	 */
	@Query(value="select * from city where name LIKE %:name% and country_id in (select id from country where continent_id = :continentId)", nativeQuery=true)
	List<CityEntity> findCitiesByPartialNameAndContinentId(@Param("name") String name, @Param("continentId") long continentId);
	
}
