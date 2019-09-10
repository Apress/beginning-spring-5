package com.bsg5.chapter7;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7888061245862993240L;

	public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(Exception e) {
        super(e);
    }
}
