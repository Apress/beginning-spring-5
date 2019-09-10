package com.bsg5.chapter10;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Song not found")
public class SongNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 4376672445756448568L;
}