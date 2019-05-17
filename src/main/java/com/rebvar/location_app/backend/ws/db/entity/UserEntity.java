package com.rebvar.location_app.backend.ws.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="user")
public class UserEntity extends AbstractUserEntity implements Serializable {
	
	private static final long serialVersionUID = 8225342489296017278L;

	@Id
	@GeneratedValue
	private long id;
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
