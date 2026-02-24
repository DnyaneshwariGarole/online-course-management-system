package com.Task.Online.Course.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error:" + exception.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception)
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + exception.getMessage());
	}

}
