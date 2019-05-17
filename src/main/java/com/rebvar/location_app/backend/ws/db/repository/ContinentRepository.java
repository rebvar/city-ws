package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.ContinentEntity;

/**
 * The Interface ContinentRepository.
 */
@Repository
public interface ContinentRepository extends CrudRepository<ContinentEntity, Long> {
	
	/**
	 * Find by unique id.
	 *
	 * @param uniqueId the unique id
	 * @return the continent entity
	 */
	ContinentEntity findByUniqueId(String uniqueId);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the continent entity
	 */
	ContinentEntity findByName(String name);
	
	/**
	 * Find continents by pattern.
	 *
	 * @param pattern the pattern
	 * @return the list
	 */
	@Query(value="select * from continent where name LIKE %:pattern%",nativeQuery=true)
	List<ContinentEntity> findContinentsByPattern(@Param("pattern") String pattern);
}
