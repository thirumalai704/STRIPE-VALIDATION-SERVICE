package com.hulkhiretech.payments.constant;

public enum ErrorCodeEnum {

	// ── Request Level ──────────────────────────────
	PAYMENT_REQUEST_NULL("10037", "Payment request must not be null"),
	USER_REQUIRED("10038", "User details are required"), PAYMENT_REQUIRED("10039", "Payment details are required"),

	// ── User ──────────────────────────────────────
	END_USER_ID_REQUIRED("10030", "End user ID is required"), FIRSTNAME_REQUIRED("10031", "First name is required"),
	LASTNAME_REQUIRED("10032", "Last name is required"), EMAIL_REQUIRED("10033", "Email is required"),
	EMAIL_INVALID("10034", "Email must be a valid email address"),
	MOBILE_PHONE_REQUIRED("10035", "Mobile phone is required"),
	MOBILE_PHONE_INVALID("10036", "Mobile phone must be a valid international format (e.g., +1234567890)"),

	// ── Payment ───────────────────────────────────
	AMOUNT_REQUIRED("10015", "Amount is required"), AMOUNT_MIN("10016", "Amount must be greater than 0"),
	BRAND_NAME_REQUIRED("10017", "Brand name is required"), LOCALE_REQUIRED("10018", "Locale is required"),
	LOCALE_INVALID("10019", "Locale format is invalid (e.g., en-US)"), COUNTRY_REQUIRED("10020", "Country is required"),
	COUNTRY_INVALID("10021", "Country must be a valid 2-letter ISO code (e.g., US, IN)"),
	MERCHANT_TXN_REF_REQUIRED("10022", "Merchant transaction reference is required"),
	MERCHANT_TXN_REF_DUPLICATE("10023", "Merchant transaction reference already exists"),
	PAYMENT_METHOD_REQUIRED("10024", "Payment method is required"),
	PAYMENT_METHOD_INVALID("10025", "Payment method is not supported"),
	PROVIDER_REQUIRED("10026", "Provider is required"), PROVIDER_INVALID("10027", "Provider is not supported"),
	PAYMENT_TYPE_REQUIRED("10028", "Payment type is required"),
	PAYMENT_TYPE_INVALID("10029", "Payment type is not supported (e.g., SALE, REFUND)"),

	// ── URL ───────────────────────────────────────
	SUCCESS_URL_REQUIRED("10001", "Success URL is required"),
	SUCCESS_URL_INVALID("10002", "Success URL must be a valid URL"),
	SUCCESS_URL_HTTPS("10003", "Success URL must start with https://"),
	CANCEL_URL_REQUIRED("10004", "Cancel URL is required"),
	CANCEL_URL_INVALID("10005", "Cancel URL must be a valid URL"),
	CANCEL_URL_HTTPS("10006", "Cancel URL must start with https://"),

	// ── Line Items ────────────────────────────────
	LIST_ITEMS_EMPTY("10007", "Line items must not be empty"), LINE_ITEM_NULL("10040", "Line item must not be null"),
	CURRENCY_REQUIRED("10008", "Currency is required"),
	CURRENCY_INVALID("10009", "Currency must be 3 uppercase letters (e.g., USD, INR)"),
	PRODUCT_NAME_REQUIRED("10010", "Product name is required"),
	UNIT_AMOUNT_REQUIRED("10011", "Unit amount is required"),
	UNIT_AMOUNT_MIN("10012", "Unit amount must be greater than 0"), QUANTITY_REQUIRED("10013", "Quantity is required"),
	QUANTITY_MIN("10014", "Quantity must be at least 1"),

	SUCCESS_URL_NOT_SECURE("10041", "Success URL must use HTTPS"),
	CANCEL_URL_NOT_SECURE("10042", "Cancel URL must use HTTPS"),

	LINE_ITEMS_REQUIRED("10043", "Line items are required"),
	LINE_ITEMS_INVALID("10044", "Line items structure invalid"),

	PAYMENT_CURRENCY_MISMATCH("10045", "Payment currency must match line item currency"),

	INVALID_REQUEST_FORMAT("10046", "Invalid request format"), VALIDATION_FAILED("10047", "Validation failed"),

	INVALID_FIRST_NAME("10048", "First name contains invalid characters"),
	INVALID_LAST_NAME("10049", "Last name contains invalid characters"),
	INVALID_AMOUNT("10050", "Amount exceeds maximum allowed value"),
	GENERIC_SERVER_ERROR("10051", "Internal Server Error"),

	FAILED_TO_SAVE_PAYMENT_REQUEST("10052", "Failed to save payment request"),
	DUPLICATE_TRANSACTION("10053", "Duplicate transaction detected"),
	INVALID_HMAC_SIGNATURE("10054", "Invalid Hmac Signature"),
	MISSING_HMAC_SIGNATURE("10055", "Missing Hmac Signature");

	private final String code;
	private final String message;

	ErrorCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}