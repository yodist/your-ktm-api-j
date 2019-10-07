package com.yodist.yourktm.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yodist.yourktm.domain.Train;
import com.yodist.yourktm.handler.ResponseHandler;
import com.yodist.yourktm.repository.TrainRepository;

@RestController
@RequestMapping("/train")
public class TrainController {

	@Autowired
	private TrainRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<ResponseHandler<List<Train>>> getAllTrains() {
		List<Train> result = repository.findAll();
		ResponseHandler<List<Train>> response = new ResponseHandler<>(result);
		return new ResponseEntity<ResponseHandler<List<Train>>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Train getTrainById(@PathVariable("id") ObjectId id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyTrainById(@PathVariable("id") ObjectId id, @Valid @RequestBody Train train) {
		train.setId(id);
		repository.save(train);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Train createTrain(@Valid @RequestBody Train train) {
		train.setId(ObjectId.get());
		repository.save(train);
		return train;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTrain(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
	}

}
