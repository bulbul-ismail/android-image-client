package com.ismail.imageservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ismail.imageservice.exception.ImageNotFoundException;

@ControllerAdvice
public class imageApiAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ImageNotFoundException.class)
	protected ResponseEntity<Object> handleConflict(ImageNotFoundException ex) {
		return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
	}
}
