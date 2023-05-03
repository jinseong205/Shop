package com.shop.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
@RestController
@Slf4j
public class ApiExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> commonApiExceptionHandler(Exception e) {
		
		return new ResponseEntity<>( new ErrorResult(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
