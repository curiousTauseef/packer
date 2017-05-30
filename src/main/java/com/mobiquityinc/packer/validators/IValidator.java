package com.mobiquityinc.packer.validators;

import com.mobiquityinc.configs.StaticPreDefined;
import com.mobiquityinc.exception.APIException;

/**
 * Validator interface that contain messages and methods to be overriden in
 * validator class
 * 
 * @author Maha M. Hamza
 *
 */
public interface IValidator {

	String NULL_INPUT = "Input Can't be null";
	String EMPTY_INPUT = "File path can't be empty";
	String INVALID_PATH = "Invalid Path";
	String INVALID_PACKAGE_WEIGHT = "Max weight that a package can take can't exceed "
			+ StaticPreDefined.MAX_WEIGHT_OF_PACKAGE;
	String INVALID_ITEMS_COUNT = "items you need to choose from can't exceed "
			+ StaticPreDefined.NUMBER_OF_ITEMS_TO_PICK_FROM;
	String INVALID_WEIGHT_COST = "Max weight and cost of an item can't exceed " + StaticPreDefined.MAX_COST_OF_ITEM;

	void validate(Object t) throws APIException;

}
