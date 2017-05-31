package com.mobiquityinc.packer.validators;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.mobiquityinc.configs.PackerConfigs;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.pojos.Package;

@Singleton
public class ValidatorService {

	@Inject
	public ValidatorService() {
	}

	/**
	 * generic method to validate inputs
	 */

	@SuppressWarnings("rawtypes")
	public Validator validate(Object t) throws APIException {
		/**
		 * null check
		 */
		if (null == t) {
			throw new APIException(PackerConfigs.getProprtyValue("NULL_INPUT"));
		} else if (t.getClass() == String.class) {
			return new StringValidator();
		} else if (t.getClass() == Package.class) {
			return new PackageValidator();
		} else {
			throw new APIException(PackerConfigs.getProprtyValue("VALIDATION_UNKNOWN_CLASS"));
		}

	}

}
