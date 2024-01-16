package com.school.sba.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHanderClass extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) 
	{
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> errors = new HashMap<String, String>();
		allErrors.forEach(error->{
			FieldError fieldError = (FieldError)error;	//Downcasting
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return structure(HttpStatus.BAD_REQUEST, "Failed to validate !!!", errors);
	}
	
	private ResponseEntity<Object> structure(HttpStatus status, String message, Object rootcause)
	{
		return new ResponseEntity<Object>(Map.of(
				"status",status.value(),
				"message",message,
				"rootcause",rootcause
					),status);
	}

	@ExceptionHandler(UniqueConstraintViolationException.class)
	public ResponseEntity<Object> handleUniqueConstraintViolation(UniqueConstraintViolationException  ex){
		return new ResponseEntity<Object>(Map.of(
				"status",HttpStatus.BAD_REQUEST.value(),
				"message", ex.getMessage(),
				"rootCause", "username or email or contact is duplicate!!"), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLDataIntegretyViolationException.class)
	public ResponseEntity<Object> handleSQLDataIntegretyViolation(SQLDataIntegretyViolationException ex){
		return new ResponseEntity<Object>(Map.of(
				"status",HttpStatus.BAD_REQUEST.value(),
				"message", ex.getMessage(),
				"rootCause", "Cannot create a 2nd admin. Admin already exists !!!"), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex)
	{
		return new ResponseEntity<Object>
			(structure(HttpStatus.NOT_FOUND, ex.getMessage(), "User not found with the given Id"), HttpStatus.NOT_FOUND);
	}
}
