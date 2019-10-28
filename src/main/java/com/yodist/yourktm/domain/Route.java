package com.yodist.yourktm.domain;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonIgnore
	private ObjectId id;

	@Field(NAME)
	private String name;

	@Field(ROUTE_CODE)
	private String routeCode;

	public Route() {
		super();
	}

	@JsonProperty(_ID)
	public String getIdAsString() {
		return id != null ? id.toHexString() : null;
	}
	
}
