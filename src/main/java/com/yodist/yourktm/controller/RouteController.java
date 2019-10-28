package com.yodist.yourktm.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodist.yourktm.domain.Route;
import com.yodist.yourktm.repository.RouteRepository;

@CrossOrigin
@RestController
@RequestMapping("/route")
public class RouteController {
	
	@Autowired
	private RouteRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Route> getAllRoutes() {
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Route getRouteById(@PathVariable("id") ObjectId id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyRouteById(@PathVariable("id") ObjectId id, @Valid @RequestBody Route route) {
		route.setId(id);
		repository.save(route);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Route createRoute(@Valid @RequestBody Route route) {
		route.setId(ObjectId.get());
		repository.save(route);
		return route;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRoute(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
	}

}
