package com.yodist.yourktm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yodist.yourktm.domain.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

	Schedule findById(ObjectId id);
	
}
