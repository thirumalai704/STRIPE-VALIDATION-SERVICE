package com.hulkhiretech.payments.service.data;

import java.util.ArrayList;
import java.util.List;

import com.hulkhiretech.payments.pojo.LineItem;
import com.hulkhiretech.payments.pojo.Payment;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.pojo.User;

public class TestDataFactory {

	public static PaymentRequest createPaymentRequest() {

		// ----- User -----
		User user = new User();
		user.setEndUserID("USER1001");
		user.setFirstname("thirumalai");
		user.setLastname("selvam");
		user.setEmail("john.doe@example.com");
		user.setMobilePhone("+14155552671");

		// ----- Line Item 1 -----
		LineItem item1 = new LineItem();
		item1.setCurrency("USD");
		item1.setProductName("Phone");
		item1.setUnitAmount(500);
		item1.setQuantity(2);

		// ----- Line Item 2 -----
		LineItem item2 = new LineItem();
		item2.setCurrency("USD");
		item2.setProductName("Headphones");
		item2.setUnitAmount(500);
		item2.setQuantity(2);

		List<LineItem> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);

		// ----- Payment -----
		Payment payment = new Payment();
		payment.setCurrency("USD");
		payment.setAmount(200);
		payment.setBrandName("MyShop");
		payment.setLocale("en-US");
		payment.setCountry("US");
		payment.setMerchantTxnRef("TXN20260305006");
		payment.setPaymentMethod("APM");
		payment.setProvider("STRIPE");
		payment.setPaymentType("SALE");
		payment.setSuccessUrl("https://example.com/success");
		payment.setCancelUrl("https://example.com/cancel");
		payment.setLineItems(items);

		// ----- Payment Request -----
		PaymentRequest request = new PaymentRequest();
		request.setUser(user);
		request.setPayment(payment);

		return request;
	}
}