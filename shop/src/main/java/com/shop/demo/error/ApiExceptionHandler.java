package com.shop.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


// Target all Controllers annotated with @RestController
@ControllerAdvice(annotations = RestController.class)
@RestController
@Slf4j
public class ApiExceptionHandler {
	
	public ResponseEntity<?> authenticationCredentialsNotFoundExceptionHandler(AuthenticationCredentialsNotFoundException e) {
		String errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		return new ResponseEntity<>( new ErrorResult(errorMessage), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException e) {
		String errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		return new ResponseEntity<>( new ErrorResult(errorMessage), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> internalAuthenticationServiceException(InternalAuthenticationServiceException e) {
		String errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
		return new ResponseEntity<>( new ErrorResult(errorMessage), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
		String errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		return new ResponseEntity<>( new ErrorResult(errorMessage), HttpStatus.BAD_REQUEST);
	}
	
	
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ResponseEntity<?> commonExceptionHandler(Exception e) {
		return new ResponseEntity<>( new ErrorResult(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
