package com.sparc.usha.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparc.usha.customException.EmailNotFoundException;
import com.sparc.usha.customException.UserNotFoundException;


/**
 * @author  Prasanjit
 * @since   18-11-22
 * @apiNote JSONException message
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

	// this method is for EmailNotFoundException json message
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmailNotFoundException.class)
	public Map<String, String> handleBusenessException(EmailNotFoundException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;

	}
	
	// this method is for UserNotFoundException json message
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleBusenessException(UserNotFoundException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", ex.getMessage());
		return error;

	}

}
