package com.yodist.yourktm.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yodist.yourktm.domain.Route;

public interface RouteRepository extends MongoRepository<Route, String> {

	Route findById(ObjectId id);
	
	Route findByRouteCode(String routeCode);
	
	List<Route> findByName(String name);
	
}
