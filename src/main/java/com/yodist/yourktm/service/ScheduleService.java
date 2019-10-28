package com.yodist.yourktm.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yodist.yourktm.domain.Schedule;

public interface ScheduleService {

	Schedule save(Schedule schedule);
	
	List<Schedule> saveMultiple(List<Schedule> scheduleList);
	
	List<Schedule> findAll();
	
	Schedule findById(ObjectId id);
	
	void delete(Schedule schedule);
	
	List<Schedule> findScheduleByCriteria(Long currentTime, String routeCode, String stationCode);
	
}
