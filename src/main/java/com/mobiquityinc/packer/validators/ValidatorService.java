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

	public void validate(Object t) throws APIException {
		/**
		 * null check
		 */
		if (null == t) {
			throw new APIException(PackerConfigs.getProprtyValue("NULL_INPUT"));
		} else if (t.getClass() == String.class) {
			new StringValidator().validate((String) t);
		} else if (t.getClass() == Package.class) {
			new PackageValidator().validate((Package) t);

		} else {
			throw new APIException(PackerConfigs.getProprtyValue("VALIDATION_UNKNOWN_CLASS"));
		}

	}

}
