package com.hulkhiretech.payments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hulkhiretech.payments.constant.ErrorCodeEnum;
import com.hulkhiretech.payments.pojo.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GolbalExceptionHandler {

	// handle the method argument not valid exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
		log.info("Handling MethodArgumentNotValidException: {}", ex.getMessage());
		String enumKey = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		ErrorCodeEnum errorMessage = ErrorCodeEnum.valueOf(enumKey);
		ErrorResponse response = new ErrorResponse(errorMessage.getMessage(), errorMessage.getCode());
		log.info("Validation error response: {}", response);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PaymentValidationException.class)
	public ResponseEntity<ErrorResponse> handleBusinessValidationException(PaymentValidationException ex) {
		log.error("Business validation error occurred: {}", ex.getMessage(), ex);
		HttpStatus status = ex.getHttpStatusCode();
		ErrorResponse response = new ErrorResponse(ex.getErrorMessage(), ex.getErrorCode());
		log.info("Business validation exception handler method completed with response: {}", response);
		return new ResponseEntity<>(response, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		log.error("An unexpected error occurred: ", ex);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ErrorCodeEnum.GENERIC_SERVER_ERROR.getCode());
		errorResponse.setMessage(ErrorCodeEnum.GENERIC_SERVER_ERROR.getMessage());
		log.info("Generic exception handler method completed errror response: {}", errorResponse);
		return new ResponseEntity<>(errorResponse, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
