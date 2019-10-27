package com.yodist.yourktm.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	
	public static final String _ID = "_id";
	public static final String CREATED_BY = "created_by";
	public static final String UPDATED_BY = "updated_by";
	public static final String CREATED_DATE = "created_date";
	public static final String UPDATED_DATE = "updated_date";
	
	@Field(CREATED_BY)
	protected String createdBy;
	
	@Field(UPDATED_BY)
	protected String updatedBy;
	
	@Field(CREATED_DATE)
	protected Date createdDate;
	
	@Field(UPDATED_DATE)
	protected Date updatedDate;
	
}
