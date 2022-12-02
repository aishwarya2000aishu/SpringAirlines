package com.speridian.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class FetchFlightsException extends RuntimeException {

	
	public FetchFlightsException(String message) {
		super(message);
		
	}



}
