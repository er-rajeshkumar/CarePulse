package com.carepulse.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String, String>> handlePatientNotFound(PatientNotFoundException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Patient not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleDoctorNotFound(DoctorNotFoundException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Doctor not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(SpecializationException.class)
	public ResponseEntity<Map<String, String>> handleSpecializationException(SpecializationException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Specialization not found");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Invalid argument Enter valid parameter");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Internal Server Error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Runtime Exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Null Pointer Exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Illegal State Exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<Map<String, String>> handleUnsupportedOperationException(UnsupportedOperationException exception) {
		Map<String, String> response = new HashMap<>();
		response.put("message", exception.getMessage());
		response.put("Issue", "Unsupported Operation Exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult()
	      .getFieldErrors()
	      .forEach(error ->
	          errors.put(
	              error.getField(),
	              error.getDefaultMessage()
	          ));

	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Validation failed");
	    response.put("errors", errors);

	    return ResponseEntity.badRequest().body(response);
	}
	
}