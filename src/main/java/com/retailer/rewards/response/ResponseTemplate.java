/**
 * 
 */
package com.retailer.rewards.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Durga
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class ResponseTemplate {

	private String customerId;
	private Integer averageRewardsPerMonth;
	private Integer accruedRewardsPerQuarter;
}