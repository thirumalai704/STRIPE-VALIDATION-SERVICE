package com.hulkhiretech.payments.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Base64;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.HmacSha256Service;
import com.hulkhiretech.payments.service.data.TestDataFactory;

public class HmacSha256ServiceTest {

	@Test
	public void testCalculateHmacSha256ProductDeterminst() {
		HmacSha256Service hmacSha256Service = new HmacSha256Service(null);
		PaymentRequest paymentRequest = TestDataFactory.createPaymentRequest();
		// convert paymentRequest to JSON string

		ObjectMapper objectMapper = new ObjectMapper();
		String dummyJson = null;
		try {
			dummyJson = objectMapper.writeValueAsString(paymentRequest);
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(dummyJson, "Failed to convert PaymentRequest to JSON");
			return;
		}
		String signature1 = hmacSha256Service.calculateHmacSha256(dummyJson);

		assertNotNull(signature1);

		byte[] decoded = Base64.getDecoder().decode(signature1);

		assertEquals(32, decoded.length);

		String signature2 = hmacSha256Service.calculateHmacSha256(dummyJson);

		assertEquals(signature1, signature2);
	}
}
