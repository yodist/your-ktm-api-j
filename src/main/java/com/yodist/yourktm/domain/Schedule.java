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
@Document(collection = "schedule")
public class Schedule extends BaseEntity {

	public static final String SCHEDULE = "schedule";
	
	public static final String TIME = "time";
	public static final String TIME_STRING = "time_string";
	public static final String SEQ = "sequence";
	
	@Id
	@Field(_ID)
	private ObjectId id;

	@Field(TIME)
	private Integer time;
	
	@Field(TIME_STRING)
	private String timeString;

	@Field(Train.TRAIN)
	private Train train;
	
	@Field(Route.ROUTE)
	private Route route;
	
	@Field(Station.STATION)
	private Station station;
	
	@Field(SEQ)
	private Integer sequence;
	
	public Schedule() {
		super();
	}

	public String getIdAsString() {
		return id != null ? id.toHexString() : null;
	}
	
}
