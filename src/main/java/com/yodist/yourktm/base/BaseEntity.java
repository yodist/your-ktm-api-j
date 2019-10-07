package com.yodist.yourktm.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	
	@Field("created_by")
	protected String createdBy;
	
	@Field("updated_by")
	protected String updatedBy;
	
	@Field("created_date")
	protected Date createdDate;
	
	@Field("updated_date")
	protected Date udpatedDate;
	
}
