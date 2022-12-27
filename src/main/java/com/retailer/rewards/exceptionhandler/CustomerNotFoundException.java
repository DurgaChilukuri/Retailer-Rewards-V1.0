/**
 * 
 */
package com.retailer.rewards.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Durga
 *
 */
@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6037960255469964018L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}