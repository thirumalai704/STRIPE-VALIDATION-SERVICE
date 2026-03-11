package com.hulkhiretech.payments.constant;

import java.util.Optional;

import com.hulkhiretech.payments.service.imp.validationrule.DuplicateTxnValidator;
import com.hulkhiretech.payments.service.interfaces.BusinessValidator;

public enum ValidationRuleEnum {
	VALIDATOR_RULE1("VALIDATOR_TXN_RULE", DuplicateTxnValidator.class);

	private final String ruleName;
	private final Class<? extends BusinessValidator> validatorClass;

	ValidationRuleEnum(String ruleName, Class<? extends BusinessValidator> validatorClass) {
		this.ruleName = ruleName;
		this.validatorClass = validatorClass;
	}

	public String getRuleName() {
		return ruleName;
	}

	public Class<? extends BusinessValidator> getvalidatorClass() {
		return validatorClass;
	}

	// find the validation rule by name and return the corresponding class or if not
	// found return isEmpty

	public static Optional<Class<? extends BusinessValidator>> getValidatorClass(String name) {
		if (name == null) {
			return Optional.empty();
		}
		for (ValidationRuleEnum rule : values()) {
			if (rule.getRuleName().equalsIgnoreCase(name)) {
				return Optional.of(rule.getvalidatorClass());
			}
		}
		return Optional.empty(); // or throw an exception if you prefer
	}

}
