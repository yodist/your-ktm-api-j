package com.yodist.yourktm.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yodist.yourktm.domain.Train;

public interface TrainService {

	Train save(Train train);
	
	List<Train> saveMultiple(List<Train> trainList);
	
	List<Train> findAll();
	
	Train findById(ObjectId id);
	
	Train findByCode(String code);
	
	void delete(Train train);
	
}
