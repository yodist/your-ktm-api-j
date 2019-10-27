package com.yodist.yourktm.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yodist.yourktm.domain.Schedule;

public interface ScheduleService {

	Schedule save(Schedule schedule);
	
	List<Schedule> saveMultiple(List<Schedule> scheduleList);
	
	List<Schedule> findAll();
	
	public Schedule findById(ObjectId id);
	
	public void delete(Schedule schedule);
	
}
