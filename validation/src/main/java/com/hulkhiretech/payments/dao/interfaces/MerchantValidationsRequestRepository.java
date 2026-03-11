package com.hulkhiretech.payments.dao.interfaces;

import com.hulkhiretech.payments.entity.MerchantValidationsRequest;

public interface MerchantValidationsRequestRepository {
	public int saveMerchantValidationsRequest(MerchantValidationsRequest merchantValidationsRequest);
}
