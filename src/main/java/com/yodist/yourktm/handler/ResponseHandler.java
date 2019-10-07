package com.yodist.yourktm.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ResponseHandler<T> {

	@JsonProperty("data")
	private T t;

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("errorMessage")
	private String errorMessage;

	
	
	public ResponseHandler(T t, String status, String message) {
		super();
		this.t = t;
		this.status = status;
		this.message = message;
	}

	public ResponseHandler(T t) {
		super();
		this.t = t;
		this.status = "200";
		this.message = "success";
	}
	
}
