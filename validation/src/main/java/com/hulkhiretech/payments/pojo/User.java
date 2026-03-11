package com.hulkhiretech.payments.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {

	@NotBlank(message = "END_USER_ID_REQUIRED")
	private String endUserID;

	@NotBlank(message = "FIRSTNAME_REQUIRED")
	private String firstname;

	@NotBlank(message = "LASTNAME_REQUIRED")
	private String lastname;

	@NotBlank(message = "EMAIL_REQUIRED")
	@Email(message = "EMAIL_INVALID")
	private String email;

	@NotBlank(message = "MOBILE_PHONE_REQUIRED")
	@Pattern(regexp = "^\\+[1-9]\\d{7,14}$", message = "MOBILE_PHONE_INVALID")
	private String mobilePhone;

}