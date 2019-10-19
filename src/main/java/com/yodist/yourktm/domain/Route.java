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
	
	@Id
	@Field("_id")
	private ObjectId id;

	@Field("name")
	private String name;

	@Field("route_code")
	private String routeCode;

	public Route() {
		super();
	}

	public String getId() {
		return id.toHexString();
	}
	
}
