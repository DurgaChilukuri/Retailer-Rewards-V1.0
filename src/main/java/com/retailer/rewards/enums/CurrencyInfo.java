/**
 *
 */
package com.retailer.rewards.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Durga
 *
 */
@AllArgsConstructor
@Getter
public enum CurrencyInfo {

	USD("USD", "$", "US dollars");

	private String currenyName;
	private String currencySymbol;
	private String currencyDescription;

}