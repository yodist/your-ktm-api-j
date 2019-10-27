package com.yodist.yourktm.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.yodist.yourktm.domain.Route;

public interface RouteService {

	Route save(Route route);

	List<Route> saveMultiple(List<Route> routeList);

	List<Route> findAll();

	Route findById(ObjectId id);

	List<Route> findByName(String name);
	
	Route findByCode(String code);

	void delete(Route route);

}
