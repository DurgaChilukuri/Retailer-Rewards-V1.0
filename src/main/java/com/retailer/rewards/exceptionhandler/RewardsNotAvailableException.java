/**
 *
 */
package com.retailer.rewards.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ghost
 *
 */
@Getter
@Setter
public class RewardsNotAvailableException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6278158631892242807L;

	public RewardsNotAvailableException(String message) {
		super(message);
	}
}