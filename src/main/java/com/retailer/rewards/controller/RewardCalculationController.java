/**
 *
 */
package com.retailer.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.response.ResponseTemplate;
import com.retailer.rewards.service.CustomerRewardsCalculatorSvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Durga
 *
 */
@RestController
@Slf4j
public class RewardCalculationController {

	@Autowired
	private CustomerRewardsCalculatorSvc customerRewardsCalculator;

	@GetMapping(path = "/api/v1/customer/{customerId}/rewards")
	public ResponseEntity<ResponseTemplate> calculcateRewards(@PathVariable String customerId) {
		log.info("calculating accrued rewards");
		Integer rewards = customerRewardsCalculator.getEarnedRewards(customerId);
		ResponseTemplate template = new ResponseTemplate(customerId, rewards, rewards / 3);
		return new ResponseEntity<>(template, HttpStatus.OK);
	}
}