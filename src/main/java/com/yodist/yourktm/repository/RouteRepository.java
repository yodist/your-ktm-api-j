package com.yodist.yourktm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yodist.yourktm.domain.Route;

public interface RouteRepository extends MongoRepository<Route, String> {

	Route findById(ObjectId id);
	
}
