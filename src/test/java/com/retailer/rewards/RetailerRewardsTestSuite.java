/**
 *
 */
package com.retailer.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.retailer.rewards.enums.CurrencyInfo;
import com.retailer.rewards.modal.Customer;
import com.retailer.rewards.modal.TransactionDetails;
import com.retailer.rewards.service.CustomerRewardsCalculatorSvcImpl;

/**
 * @author Durga
 *
 */
class RetailerRewardsTestSuite {

	private CustomerRewardsCalculatorSvcImpl customerRewardsCalculatorSvcImpl;

	@BeforeEach
	public void setup() {
		customerRewardsCalculatorSvcImpl = new CustomerRewardsCalculatorSvcImpl();

	}

	static Stream<List<Customer>> getMockData() {

		/**
		 * some transactions are already awarded and some transactions amount is less
		 * than $50
		 */
		TransactionDetails t1c4 = new TransactionDetails(true, CurrencyInfo.USD, 1000.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "7");
		TransactionDetails t2c4 = new TransactionDetails(false, CurrencyInfo.USD, 49.0,
				LocalDate.of(2022, 12, 12).atStartOfDay(), "8");

		Customer customer = new Customer(0, 0, "C1", Arrays.asList(t1c4, t2c4));

		return Stream.of(Arrays.asList(customer));
	}

	@MethodSource("getMockData")
	@ParameterizedTest
	void assertRewards(List<Customer> customerData) {
		assertEquals(0,
				customerRewardsCalculatorSvcImpl.getEarnedRewards(customerData.get(0).getCustomerId(), customerData));
	}
}
