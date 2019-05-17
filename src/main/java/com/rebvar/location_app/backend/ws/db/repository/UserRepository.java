package com.rebvar.location_app.backend.ws.db.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rebvar.location_app.backend.ws.db.entity.UserEntity;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user entity
	 */
	UserEntity findByEmail(String email);
	
	/**
	 * Find by user id.
	 *
	 * @param id the id
	 * @return the user entity
	 */
	UserEntity findByUserId(String id);
	
	/**
	 * Find all by name.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<UserEntity> findAllByName(String id);
	
	

	/**
	 * Find users by pattern.
	 *
	 * @param pattern the pattern
	 * @return Searches the table for partial name match. Custom query
	 */
	@Query(value="select * from User where name LIKE %:pattern%",nativeQuery=true)
	List<UserEntity> findUsersByPattern(@Param("pattern") String pattern);
	
}
