/**
 * 
 */
package com.retailer.rewards.modal;

import java.time.LocalDateTime;

import com.retailer.rewards.enums.CurrencyInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ghost
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {

	private Boolean isTransactionAlreadyRewarded;
	private CurrencyInfo currencyInfo;
	private Double totalSpentAmount;
	private LocalDateTime transactionDate;
	private String transactionId;
}