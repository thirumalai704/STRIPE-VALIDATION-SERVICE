package com.hulkhiretech.payments.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.Constant;
import com.hulkhiretech.payments.constant.ErrorCodeEnum;
import com.hulkhiretech.payments.exception.PaymentValidationException;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.util.JsonUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HmacSha256Service {

	@Value("${hmac.secret-key}")
	private String secretKey;

	private final JsonUtil jsonUtil;

	public String calculateHmacSha256(String data) {
		String hmacSignature = null;

		try {

			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), Constant.HMAC_SHA256);

			Mac mac = Mac.getInstance(Constant.HMAC_SHA256);

			mac.init(secretKeySpec);

			byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

			hmacSignature = java.util.Base64.getEncoder().encodeToString(hmacBytes);

		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hmacSignature;
	}

	public String isHmacShaValid(PaymentRequest paymentRequest, String hmacSignature) {

		if (hmacSignature == null || hmacSignature.isEmpty()) {
			log.error("HMAC signature is missing in the request");
			throw new PaymentValidationException(ErrorCodeEnum.MISSING_HMAC_SIGNATURE.getMessage(),
					ErrorCodeEnum.MISSING_HMAC_SIGNATURE.getCode(), HttpStatus.BAD_REQUEST);
		}

		String jsonString = jsonUtil.convertObjectToJson(paymentRequest);

		// call method directly
		String calculatedHmac = calculateHmacSha256(jsonString);

		if (!calculatedHmac.equals(hmacSignature)) {
			log.error("HMAC signature mismatch. Calculated: {}, Received {}", calculatedHmac, hmacSignature);

			throw new PaymentValidationException(ErrorCodeEnum.INVALID_HMAC_SIGNATURE.getMessage(),
					ErrorCodeEnum.INVALID_HMAC_SIGNATURE.getCode(), HttpStatus.UNAUTHORIZED);
		}
		return calculatedHmac;
	}
}