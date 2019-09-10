package com.bsg5.chapter8;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Artist not found")
public class ArtistNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7057185664051689118L;
}
