package com.yodist.yourktm.domain;

import java.util.Date;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yodist.yourktm.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "schedule")
public class Schedule extends BaseEntity {

	@Id
	@Field("_id")
	private ObjectId id;

	@Field("time")
	private Date time;
	
	@Field("time_string")
	private String timeString;

	@Field("train")
	private Train train;
	
	@Field("route")
	private Route route;
	
	@Field("station")
	private Station station;
	
	@Field("sequence")
	private Integer sequence;
	
	public Schedule() {
		super();
	}

	public String getId() {
		return id.toHexString();
	}
	
}
