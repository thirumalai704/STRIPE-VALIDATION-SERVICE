package com.hulkhiretech.payments.pojo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

	@NotNull(message = "USER_REQUIRED")
	@Valid
	private User user;

	@NotNull(message = "PAYMENT_REQUIRED")
	@Valid
	private Payment payment;

}