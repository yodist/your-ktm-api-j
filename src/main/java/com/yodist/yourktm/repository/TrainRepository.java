package com.yodist.yourktm.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yodist.yourktm.domain.Train;

public interface TrainRepository extends MongoRepository<Train, String>{
	
	Train findById(ObjectId id);
	
	Train findByTrainCode(String trainCode);
	
	List<Train> findByName(String name);
	
}
