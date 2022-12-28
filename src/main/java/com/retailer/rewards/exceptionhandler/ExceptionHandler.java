/**
 *
 */
package com.retailer.rewards.exceptionhandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Durga
 *
 */
@ControllerAdvice
public class ExceptionHandler {

	private static final String TIMESTAMP = "timestamp";
	private static final String ERRORS = "errors";

	@org.springframework.web.bind.annotation.ExceptionHandler(RewardsNotAvailableException.class)
	public ResponseEntity<Object> resourceNotFoundException(RewardsNotAvailableException rnaEx, WebRequest req) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(ERRORS, rnaEx.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(CustomerNotFoundException cnfEx, WebRequest req) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, LocalDateTime.now());
		body.put(ERRORS, cnfEx.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}