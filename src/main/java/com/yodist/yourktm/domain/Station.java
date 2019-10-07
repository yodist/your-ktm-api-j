package com.yodist.yourktm.domain;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yodist.yourktm.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Document(collection = "station")
public class Station extends BaseEntity {

	@Id
	@Field("_id")
	private ObjectId id;
	
	@Field("name")
	private String name;
	
	@Field("train_code")
	private String trainCode;
	
	public Station() {
		super();
	}	
	
	public String getId() {
		return id.toHexString();
	}

}
