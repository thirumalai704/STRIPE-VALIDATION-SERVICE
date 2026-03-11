package com.hulkhiretech.payments.dao.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.constant.ErrorCodeEnum;
import com.hulkhiretech.payments.dao.interfaces.MerchantValidationsRequestRepository;
import com.hulkhiretech.payments.entity.MerchantValidationsRequest;
import com.hulkhiretech.payments.exception.PaymentValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MerchantValidationsRequestRepositoryImpl implements MerchantValidationsRequestRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String INSERT_SQL = """
			INSERT INTO merchant_payment_request
			(endUserID, merchantTxnReference, transactionRequest)
			VALUES (:endUserID, :merchantTxnReference, :transactionRequest)
			""";

	@Override
	public int saveMerchantValidationsRequest(MerchantValidationsRequest merchantValidationsRequest) {

		log.info("Saving MerchantValidationsRequest: {}", merchantValidationsRequest);

		MapSqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("endUserID", merchantValidationsRequest.getEndUserID())
				.addValue("merchantTxnReference", merchantValidationsRequest.getMerchantTxnReference())
				.addValue("transactionRequest", merchantValidationsRequest.getTransactionRequest());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			namedParameterJdbcTemplate.update(INSERT_SQL, parameters, keyHolder, new String[] { "id" });

			Number generatedId = keyHolder.getKey();

			log.info("Insert operation completed for MerchantValidationsRequest: {}, generated id: {}",
					merchantValidationsRequest, generatedId);

			if (generatedId != null) {
				log.info("Merchant payment request inserted with id : {}", generatedId);
				return generatedId.intValue();
			}
			log.error("Failed to retrieve generated id after inserting merchant payment request : {}",
					merchantValidationsRequest);

			throw new PaymentValidationException(ErrorCodeEnum.FAILED_TO_SAVE_PAYMENT_REQUEST.getCode(),
					ErrorCodeEnum.FAILED_TO_SAVE_PAYMENT_REQUEST.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (DuplicateKeyException ex) {

			log.error("Duplicate merchantTxnReference detected : {}",
					merchantValidationsRequest.getMerchantTxnReference(), ex);
			return -1;
		}
	}
}
