package com.rebvar.location_app.backend.ws.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class UserEntity.
 */
@Entity(name="user")
public class UserEntity extends AbstractUserEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8225342489296017278L;

	/** The id. */
	@Id
	@GeneratedValue
	private long id;
		
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
}
