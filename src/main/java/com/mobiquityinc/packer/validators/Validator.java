package com.mobiquityinc.packer.validators;

import com.mobiquityinc.exception.APIException;

/**
 * Validator interface that contain messages and methods to be overriden in
 * validator class
 * 
 * @author Maha M. Hamza
 *
 */
public interface Validator<T> {

	void validate(T t) throws APIException;

}
