package com.hulkhiretech.payments.pojo;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Payment {
	@NotBlank(message = "CURRENCY_REQUIRED")
	@Pattern(regexp = "^[A-Z]{3}$", message = "CURRENCY_INVALID")
	private String currency;

	@NotNull(message = "AMOUNT_REQUIRED")
	@Min(value = 1, message = "AMOUNT_MIN")
	private Integer amount;

	@NotBlank(message = "BRAND_NAME_REQUIRED")
	private String brandName;

	@NotBlank(message = "LOCALE_REQUIRED")
	@Pattern(regexp = "^[a-z]{2}-[A-Z]{2}$", message = "LOCALE_INVALID")
	private String locale;

	@NotBlank(message = "COUNTRY_REQUIRED")
	@Pattern(regexp = "^[A-Z]{2}$", message = "COUNTRY_INVALID")
	private String country;

	@NotBlank(message = "MERCHANT_TXN_REF_REQUIRED")
	private String merchantTxnRef;

	@NotBlank(message = "PAYMENT_METHOD_REQUIRED")
	private String paymentMethod;

	@NotBlank(message = "PROVIDER_REQUIRED")
	private String provider;

	@NotBlank(message = "PAYMENT_TYPE_REQUIRED")
	private String paymentType;

	@NotBlank(message = "SUCCESS_URL_REQUIRED")
	@Pattern(regexp = "^https://.*", message = "SUCCESS_URL_HTTPS")
	private String successUrl;

	@NotBlank(message = "CANCEL_URL_REQUIRED")
	@Pattern(regexp = "^https://.*", message = "CANCEL_URL_HTTPS")
	private String cancelUrl;

	@NotEmpty(message = "LIST_ITEMS_EMPTY")
	@Valid
	private List<LineItem> lineItems;
}
