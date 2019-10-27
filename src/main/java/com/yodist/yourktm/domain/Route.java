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
@Document(collection = "route")
public class Route extends BaseEntity {
	
	public static final String ROUTE = "route";
	
	public static final String NAME = "name";
	public static final String ROUTE_CODE = "route_code";
	
	@Id
	@Field(_ID)
	private ObjectId id;

	@Field(NAME)
	private String name;

	@Field(ROUTE_CODE)
	private String routeCode;

	public Route() {
		super();
	}

	public String getIdAsString() {
		return id != null ? id.toHexString() : null;
	}
	
}
