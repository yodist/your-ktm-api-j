package com.yodist.yourktm.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ResponseHandler {

	@JsonProperty("data")
	private Object data;

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("errorMessage")
	private String errorMessage;

	public ResponseHandler() {}
	
	public ResponseHandler(Object data, String status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}
	
	public ResponseHandler(Object data, String status, String message, String errorMessage) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
		this.errorMessage = errorMessage;
	}

}
