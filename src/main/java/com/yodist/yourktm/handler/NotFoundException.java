package com.yodist.yourktm.handler;

public class NotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String domain, Long id) {
		super("Could not find " + domain + " with ID: " + id);
	}
	
}
