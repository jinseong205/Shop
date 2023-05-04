package com.shop.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
@RestController
public class ApiExceptionHandler {

	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ResponseEntity<?> commonExceptionHandler(Exception e) {
		return new ResponseEntity<>( new ErrorResult(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
