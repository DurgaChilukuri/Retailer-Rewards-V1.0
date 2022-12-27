/**
 *
 */
package com.retailer.rewards.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Durga
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private Integer accruedRewardsPerQuarter;
	private Integer averageRewardsPerMonth;
	private String customerId;
	private List<TransactionDetails> transactionDetailsList;
}