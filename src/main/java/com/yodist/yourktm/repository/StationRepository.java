package com.yodist.yourktm.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yodist.yourktm.domain.Station;

public interface StationRepository extends MongoRepository<Station, String> {

	Station findById(ObjectId id);
	
	Station findByStationCode(String stationCode);
	
	List<Station> findByName(String name);

}
