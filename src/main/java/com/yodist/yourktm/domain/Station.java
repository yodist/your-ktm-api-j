package com.yodist.yourktm.domain;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yodist.yourktm.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "station")
public class Station extends BaseEntity {

	public static final String STATION = "station";
	
	public static final String NAME = "name";
	public static final String STATION_CODE = "station_code";
	
	@Id
	@Field(_ID)
	private ObjectId id;

	@Field(NAME)
	private String name;

	@Field(STATION_CODE)
	private String stationCode;

	public Station() {
		super();
	}

	public String getIdAsString() {
		return id != null ? id.toHexString() : null;
	}

}
