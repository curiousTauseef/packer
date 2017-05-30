package com.mobiquityinc.packer.validators;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import com.mobiquityinc.configs.StaticPreDefined;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.PathValidationException;
import com.mobiquityinc.packer.pojos.Package;

public class Validator implements IValidator {

	/**
	 * generic method to validate inputs
	 */
	@Override
	public void validate(Object t) throws APIException {
		/**
		 * null check
		 */
		if (null == t) {
			throw new APIException(NULL_INPUT);
		}
		/**
		 * string validation
		 */
		else if (t.getClass() == String.class) {
			if ((((String) t).replaceAll(" ", "").length() == 0)) {
				throw new APIException(EMPTY_INPUT);
			}
			try {
				Paths.get((String) t);
			} catch (InvalidPathException | NullPointerException e) {
				throw new PathValidationException(INVALID_PATH, e.getMessage());
			}
		}
		/**
		 * package validation<br/>
		 * 1. Max weight that a package can take is ≤ 100<br/>
		 * 2.There might be up to 15 items you need to choose from <br/>
		 * 3. Max weight and cost of an item is ≤ 100<br/>
		 */
		else if (t.getClass() == Package.class) {
			Package pkg = (Package) t;
			if (pkg.getPackageMaximumWeight() > StaticPreDefined.MAX_WEIGHT_OF_PACKAGE) {
				throw new APIException(INVALID_PACKAGE_WEIGHT);
			} else if (pkg.getItems().size() > StaticPreDefined.NUMBER_OF_ITEMS_TO_PICK_FROM) {
				throw new APIException(INVALID_ITEMS_COUNT);
			} else {
				pkg.getItems().forEach(item -> {
					if (item.getWeight() > StaticPreDefined.MAX_WEIGHT_OF_ITEM
							|| item.getCost() > StaticPreDefined.MAX_COST_OF_ITEM) {
						throw new APIException(INVALID_WEIGHT_COST);
					}
				});
			}

		}

	}

}
