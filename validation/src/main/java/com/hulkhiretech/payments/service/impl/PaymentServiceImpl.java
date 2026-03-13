package com.hulkhiretech.payments.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.ValidationRuleEnum;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.HmacSha256Service;
import com.hulkhiretech.payments.service.interfaces.BusinessValidator;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	@Value("${validator.rule-name}")
	private String validationRuleName;

	private final ApplicationContext applicationContext;

	private final HmacSha256Service hmacSha256Service;

	@Override
	public String validateAndCreatePayment(PaymentRequest paymentRequest, String hmacSignature) {

		log.info("Validation rule name Recevied : {}", validationRuleName);
		log.info("Received payment request: {}", paymentRequest);
		log.info("hamcsha256 for payment request: {}, {}", paymentRequest, hmacSignature);

		String computeSignature = hmacSha256Service.isHmacShaValid(paymentRequest, hmacSignature);

		String[] rule = validationRuleName.split(",");

		for (String r : rule) {
			log.info("Applying validation rule: {}", r);
			Optional<Class<? extends BusinessValidator>> validatorClassOpt = ValidationRuleEnum.getValidatorClass(r);

			if (!validatorClassOpt.isPresent()) {
				log.warn("No validator found for rule: {}", r);
				continue; // or handle as needed
			}

			BusinessValidator businessValidator = applicationContext.getBean(validatorClassOpt.get());

			if (businessValidator == null) {
				log.warn(r);
				continue;
			}
			businessValidator.validator(paymentRequest);
		}

		log.info("All validation rules applied successfully for payment request: {}", paymentRequest);

		String paymentResult = "payment created successfully \n " + paymentRequest.toString();
		log.info("Payment validation result: {}", paymentResult);
		return paymentResult;
	}

}
