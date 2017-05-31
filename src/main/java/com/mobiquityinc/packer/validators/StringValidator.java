package com.mobiquityinc.packer.validators;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import com.mobiquityinc.configs.PackerConfigs;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.PathValidationException;

/**
 * string validation
 */
public class StringValidator implements Validator<String> {

	@Override
	public void validate(String t) throws APIException {
		if ((((String) t).replaceAll(" ", "").length() == 0)) {
			throw new APIException(PackerConfigs.getProprtyValue("EMPTY_INPUT"));
		}
		try {
			Paths.get((String) t);
		} catch (InvalidPathException | NullPointerException e) {
			throw new PathValidationException(PackerConfigs.getProprtyValue("INVALID_PATH"), e.getMessage());
		}

	}

}
