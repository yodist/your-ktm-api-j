package com.yodist.yourktm.controller;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodist.yourktm.base.BaseController;
import com.yodist.yourktm.domain.Train;
import com.yodist.yourktm.handler.ResponseHandler;
import com.yodist.yourktm.repository.TrainRepository;

@RestController
@RequestMapping("/train")
public class TrainController extends BaseController {

	@Autowired
	private TrainRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<ResponseHandler> getAllTrains() {
		return ok(repository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseHandler> getTrainById(@PathVariable("id") ObjectId id) {
		return ok(repository.findById(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHandler> modifyTrainById(@PathVariable("id") ObjectId id, @Valid @RequestBody Train train) {
		train.setId(id);
		return ok(repository.save(train));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<ResponseHandler> createTrain(@Valid @RequestBody Train train) {
		train.setId(ObjectId.get());
		repository.save(train);
		return ok(train);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHandler> deleteTrain(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
		return ok("Delete data success");
	}

}
