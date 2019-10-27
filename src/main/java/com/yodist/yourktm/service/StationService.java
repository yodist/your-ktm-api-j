package com.yodist.yourktm.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yodist.yourktm.domain.Station;

public interface StationService {

Station save(Station station);
	
	List<Station> saveMultiple(List<Station> stationList);
	
	List<Station> findAll();
	
	Station findById(ObjectId id);
	
	Station findByCode(String code);
	
	void delete(Station station);
	
}
