package com.hulkhiretech.payments.service.imp.validationrule;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.ErrorCodeEnum;
import com.hulkhiretech.payments.dao.interfaces.MerchantValidationsRequestRepository;
import com.hulkhiretech.payments.entity.MerchantValidationsRequest;
import com.hulkhiretech.payments.exception.PaymentValidationException;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.BusinessValidator;
import com.hulkhiretech.payments.util.JsonUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DuplicateTxnValidator implements BusinessValidator {

	private final MerchantValidationsRequestRepository merchantValidationsRequestRepository;

	private final JsonUtil jsonUtil;

	@Override
	public void validator(PaymentRequest paymentRequest) {
		log.info("Executing ValidationRule1 for payment request: {}", paymentRequest);

		MerchantValidationsRequest entity = new MerchantValidationsRequest();

		entity.setEndUserID(paymentRequest.getUser().getEndUserID());
		entity.setMerchantTxnReference(paymentRequest.getPayment().getMerchantTxnRef());
		entity.setTransactionRequest(jsonUtil.convertObjectToJson(paymentRequest));

		int pkId = merchantValidationsRequestRepository.saveMerchantValidationsRequest(entity);

		log.info("Repository returned primary key id: {}", pkId);

		if (pkId == -1) {// duplicate transaction detected
			log.error("Failed to save merchant payment request, possible duplicate transaction. Payment request: {}",
					paymentRequest);

			throw new PaymentValidationException(ErrorCodeEnum.DUPLICATE_TRANSACTION.getCode(),
					ErrorCodeEnum.DUPLICATE_TRANSACTION.getMessage(), HttpStatus.BAD_REQUEST);
		}

		log.info("Payment request is valid, " + "no duplicate transaction detected. " + "Payment request: {}",
				paymentRequest);
//		log.info("ValidationRule1 passed for payment request: {}");
	}

}
