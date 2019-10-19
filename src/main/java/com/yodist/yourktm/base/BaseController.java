package com.yodist.yourktm.base;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yodist.yourktm.handler.ResponseHandler;

@MappedSuperclass
public abstract class BaseController {
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected ResponseEntity<ResponseHandler> ok() {
		return ok(null, "Success");
	}
	
	/**
	 * Method to handle success operation
	 * @param data {@code Object}
	 * @return will return standardized response 
	 */
	protected ResponseEntity<ResponseHandler> ok(Object data) {
		return ok(data, "Success");
	}
	
	protected ResponseEntity<ResponseHandler> ok(String message) {
		return ok(null, message);
	}
	
	protected ResponseEntity<ResponseHandler> ok(Object data, String message) {
		return ok(data, null, message);
	}
	
	protected ResponseEntity<ResponseHandler> ok(Object data, String status, String message) {
		if (StringUtils.isBlank(status)) status = String.valueOf(HttpStatus.OK.value());
		ResponseHandler response = new ResponseHandler(data, status, message);
		return new ResponseEntity<ResponseHandler>(response, HttpStatus.OK);
	}
	
	protected ResponseEntity<ResponseHandler> somethingGoesWrong(String message, String errorMessage) {
		return somethingGoesWrong(null, message, errorMessage);
	}
	
	protected ResponseEntity<ResponseHandler> somethingGoesWrong(String status, String message, String errorMessage) {
		return somethingGoesWrong(null, status, message, errorMessage);
	}
	
	protected ResponseEntity<ResponseHandler> somethingGoesWrong(Object data, String status, String message, String errorMessage) {
		if (StringUtils.isBlank(status)) status = String.valueOf(HttpStatus.BAD_REQUEST.value());
		ResponseHandler response = new ResponseHandler(data, status, message, errorMessage);
		return new ResponseEntity<ResponseHandler>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * NEED TO CREATE GLOBAL EXCEPTION HANDLER LATER
	 */

}
