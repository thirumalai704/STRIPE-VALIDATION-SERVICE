package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.PaymentRequest;

import jakarta.validation.Valid;

public interface PaymentService {

	String validateAndCreatePayment(@Valid PaymentRequest paymentRequest);

}
