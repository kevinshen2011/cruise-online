package com.caribbean.online.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errMsg = ex.toString();		
		ErrorMessage errDetails = new ErrorMessage(new Date(), errMsg);
		
		return new ResponseEntity<>(errDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {NullPointerException.class, CustomerException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
		String errMsg = ex.toString();		
		ErrorMessage errDetails = new ErrorMessage(new Date(), errMsg);
		
		return new ResponseEntity<>(errDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
