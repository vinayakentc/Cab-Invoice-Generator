package com.invoiceservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
	InvoiceGenerator invoiceGenerator = null;

	@Before
	public void setUp() throws Exception {
		invoiceGenerator = new InvoiceGenerator();
	}

	@Test
	public void givenDistanceAndTimeReturnTotalFare() {
		double distance = 2.5;
		int time = 10;
		String rideType="normal";
		double fare = invoiceGenerator.calculateFare(distance, time,rideType);
		Assert.assertEquals(35, fare, 0.0);
	}

	@Test
	public void givenLessDistanceAndTimeShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 1;
		String rideType="premium";
		double fare = invoiceGenerator.calculateFare(distance, time,rideType);
		Assert.assertEquals(20, fare, 0.0);
	}

	@Test
	public void givenMultipleRidesShouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(2.0, 5,"normal"), new Ride(0.1, 1,"premium") };
		InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}

	@Test
	public void givenUserIdShouldReturnTheInvoiceSummary() {
		int userId = 2;
		RideRepository rideRepository = new RideRepository();
		Ride[] rideList = rideRepository.getRideList(userId);
		InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rideList);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 100.0);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}
}