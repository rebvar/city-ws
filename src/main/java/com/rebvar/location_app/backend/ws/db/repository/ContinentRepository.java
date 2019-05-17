package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.ContinentEntity;

@Repository
public interface ContinentRepository extends CrudRepository<ContinentEntity, Long> {
	ContinentEntity findByUniqueId(String uniqueId);
	ContinentEntity findByName(String name);
	
	@Query(value="select * from continent where name LIKE %:pattern%",nativeQuery=true)
	List<ContinentEntity> findContinentsByPattern(@Param("pattern") String pattern);
}
