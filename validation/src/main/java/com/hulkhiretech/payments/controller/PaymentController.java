package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping
	public String validatePayment(@Valid @RequestBody PaymentRequest paymentRequest,
			@RequestHeader(value = "Hmac-Signature", required = false) String hmacSignature) {

		log.info("Received payment request with HMAC signature: {}", hmacSignature);
		String serviceResponse = paymentService.validateAndCreatePayment(paymentRequest, hmacSignature);
		log.info("Received payment request: {}", paymentRequest);
		return serviceResponse;
	}
}
