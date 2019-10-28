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

import com.yodist.yourktm.domain.Station;
import com.yodist.yourktm.repository.StationRepository;

@CrossOrigin
@RestController
@RequestMapping("/station")
public class StationController {
	
	@Autowired
	private StationRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Station> getAllStations() {
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Station getStationById(@PathVariable("id") ObjectId id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyStationById(@PathVariable("id") ObjectId id, @Valid @RequestBody Station station) {
		station.setId(id);
		repository.save(station);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Station createStation(@Valid @RequestBody Station station) {
		station.setId(ObjectId.get());
		repository.save(station);
		return station;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteStation(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
	}
	
}
