package com.hulkhiretech.payments.pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LineItem {
	@NotBlank(message = "CURRENCY_REQUIRED")
	@Pattern(regexp = "^[A-Z]{3}$", message = "CURRENCY_INVALID")
	private String currency;

	@NotBlank(message = "PRODUCT_NAME_REQUIRED")
	private String productName;

	@NotNull(message = "UNIT_AMOUNT_REQUIRED")
	@Min(value = 1, message = "UNIT_AMOUNT_MIN")
	private Integer unitAmount;

	@NotNull(message = "QUANTITY_REQUIRED")
	@Min(value = 1, message = "QUANTITY_MIN")
	private Integer quantity;
}