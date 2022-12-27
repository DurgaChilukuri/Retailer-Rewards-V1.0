/**
 *
 */
package com.retailer.rewards.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.retailer.rewards.enums.CurrencyInfo;
import com.retailer.rewards.exceptionhandler.CustomerNotFoundException;
import com.retailer.rewards.exceptionhandler.RewardsNotAvailableException;
import com.retailer.rewards.modal.Customer;
import com.retailer.rewards.modal.TransactionDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Durga
 *
 */
@Service
@Slf4j
public class CustomerRewardsCalculatorSvcImpl implements CustomerRewardsCalculatorSvc {

	protected List<Customer> customersTransactionData;

	public CustomerRewardsCalculatorSvcImpl() {
		this.customersTransactionData = getMockData();
	}

	@Override
	public Integer getEarnedRewards(String customerId) {
		log.debug("calculating accrued rewards");
		List<TransactionDetails> customerTransDetails = this.getCustomerTransactionsDetails(customerId);

		Integer rewards = this.findUnrewardedAmountSpentInAQuarter(customerTransDetails);

		log.debug("setting reward point values to monthly averages and quarterly elgibile rewards");
		customersTransactionData.stream().filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerId))
				.forEach(customer -> {
					customer.setAccruedRewardsPerQuarter(rewards);
					customer.setAverageRewardsPerMonth(rewards);
				});
		return rewards;
	}

	public Integer getEarnedRewards(String customerId, List<Customer> customerData) {

		this.customersTransactionData = customerData;
		return getEarnedRewards(customerId);
	}

	private List<TransactionDetails> getCustomerTransactionsDetails(String customerId) {
		log.debug("fetching transactional record of each consumer");

		Optional<List<TransactionDetails>> customerTransactionDetails = customersTransactionData.stream()
				.filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerId))
				.map(Customer::getTransactionDetailsList).findAny();

		if (!customerTransactionDetails.isPresent()) {
			log.error("customer not found");
			throw new CustomerNotFoundException("customer with Id " + customerId + " is not found. please verify");
		}
		return customerTransactionDetails.get();
	}

	private Integer findUnrewardedAmountSpentInAQuarter(List<TransactionDetails> customerTransactionDetails) {
		log.debug("finding reward eligible transactions in a quarter for this customer");

		List<TransactionDetails> rewardsEligibleTransactionList = customerTransactionDetails.stream()
				.filter(transaction -> !transaction.getIsTransactionAlreadyRewarded())
				.filter(transaction -> (Duration
						.between(LocalDate.of(2022, 5, 15).atStartOfDay(), transaction.getTransactionDate())
						.toDays() < 90))
				.toList();

		Double totalSpentAmount = rewardsEligibleTransactionList.stream().map(TransactionDetails::getTotalSpentAmount)
				.reduce(0.00, (sum, elt) -> sum + elt);

		if (totalSpentAmount == null || totalSpentAmount == 0.00) {
			log.error("no rewards earned");
			throw new RewardsNotAvailableException("rewards are not available or already redeemed");
		}

		Double rewardPointsForSpentAmountGreaterThan50 = totalSpentAmount - Double.valueOf(50.0) > 1.00
				? totalSpentAmount - Double.valueOf(50.0) * 1
				: 0;
		Double rewardPointsForSpentAmountGreaterThan100 = totalSpentAmount - Double.valueOf(100.0) > 1.00
				? totalSpentAmount - Double.valueOf(50.0) * 1
				: 0;

		Integer accruedRewards = rewardPointsForSpentAmountGreaterThan50.intValue()
				+ rewardPointsForSpentAmountGreaterThan100.intValue();
		log.debug("reward points accrued by customer : {} ", accruedRewards);
		this.updatePaidRewardsStatus(rewardsEligibleTransactionList);
		return accruedRewards;
	}

	private void updatePaidRewardsStatus(List<TransactionDetails> rewardedTransactionsList) {
		log.debug("updating the status of the rewarded transactions");
		rewardedTransactionsList.stream().forEach(trans -> trans.setIsTransactionAlreadyRewarded(true));
	}

	private List<Customer> getMockData() {

		log.debug("preparing and injecting mock data during application boot up");
		/**
		 * one transaction is lesser than $50 and one transaction greater than $100
		 */

		TransactionDetails trans1cust1 = new TransactionDetails(true, CurrencyInfo.USD, 33.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "1");
		TransactionDetails trans2cust1 = new TransactionDetails(false, CurrencyInfo.USD, 100.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "2");

		Customer customer1 = new Customer(0, 0, "C1", Arrays.asList(trans1cust1, trans2cust1));

		/**
		 * Both transactions are greater than $100
		 */
		TransactionDetails trans1cust2 = new TransactionDetails(false, CurrencyInfo.USD, 111.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "3");
		TransactionDetails trans2cust2 = new TransactionDetails(true, CurrencyInfo.USD, 222.22,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "4");

		Customer customer2 = new Customer(0, 0, "C2", Arrays.asList(trans1cust2, trans2cust2));

		/**
		 * Both transactions are less than $50
		 */
		TransactionDetails trans1cust3 = new TransactionDetails(false, CurrencyInfo.USD, 44.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "5");
		TransactionDetails trans2cust3 = new TransactionDetails(false, CurrencyInfo.USD, 25.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "6");

		Customer customer3 = new Customer(0, 0, "C3", Arrays.asList(trans1cust3, trans2cust3));

		/**
		 * some transactions are already rewarded and some transactions are less than
		 * $50 and not rewarded
		 */
		TransactionDetails trans1cust4 = new TransactionDetails(true, CurrencyInfo.USD, 999.99,
				LocalDate.of(2021, 12, 12).atStartOfDay(), "7");
		TransactionDetails trans2cust4 = new TransactionDetails(false, CurrencyInfo.USD, 49.99,
				LocalDate.of(2021, 12, 12).atStartOfDay(), "8");

		Customer customer4 = new Customer(0, 0, "C4", Arrays.asList(trans1cust4, trans2cust4));

		/**
		 * one transaction is less than $50 one transaction greater than $50 but less
		 * than $100
		 */
		TransactionDetails trans1cust5 = new TransactionDetails(true, CurrencyInfo.USD, 11.99,
				LocalDate.of(2021, 12, 12).atStartOfDay(), "9");
		TransactionDetails trans2cust5 = new TransactionDetails(false, CurrencyInfo.USD, 69.99,
				LocalDate.of(2021, 12, 12).atStartOfDay(), "10");

		Customer customer5 = new Customer(0, 0, "C5", Arrays.asList(trans1cust5, trans2cust5));

		return Arrays.asList(customer1, customer2, customer3, customer4, customer5);
	}

}