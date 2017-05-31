package com.mobiquityinc.packer.validators;

import com.mobiquityinc.configs.StaticPreDefinedData;
import com.mobiquityinc.exception.APIException;

/**
 * Validator interface that contain messages and methods to be overriden in
 * validator class
 * 
 * @author Maha M. Hamza
 *
 */
public interface Validator {

	String NULL_INPUT = "Input Can't be null";
	String EMPTY_INPUT = "File path can't be empty";
	String INVALID_PATH = "Invalid Path";
	String INVALID_PACKAGE_WEIGHT = "Max weight that a package can take can't exceed "
			+ StaticPreDefinedData.MAX_WEIGHT_OF_PACKAGE;
	String INVALID_ITEMS_COUNT = "items you need to choose from can't exceed "
			+ StaticPreDefinedData.NUMBER_OF_ITEMS_TO_PICK_FROM;
	String INVALID_WEIGHT_COST = "Max weight and cost of an item can't exceed " + StaticPreDefinedData.MAX_COST_OF_ITEM;

	void validate(Object t) throws APIException;

}
