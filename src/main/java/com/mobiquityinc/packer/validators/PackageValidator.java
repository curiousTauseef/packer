package com.mobiquityinc.packer.validators;

import com.mobiquityinc.configs.PackerConfigs;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.pojos.Package;

/**
 * package validation<br/>
 * 1. Max weight that a package can take is 100<br/>
 * 2. There might be up to 15 items you need to choose from <br/>
 * 3. Max weight and cost of an item is ≤ 100<br/>
 */
public class PackageValidator implements Validator<Package> {

	@Override
	public void validate(Package pkg) throws APIException {
		if (pkg.getPackageMaximumWeight() > PackerConfigs.MAX_WEIGHT_OF_PACKAGE) {
			throw new APIException(
					PackerConfigs.getProprtyValue("INVALID_PACKAGE_WEIGHT", PackerConfigs.MAX_WEIGHT_OF_PACKAGE));
		} else if (pkg.getItems().size() > PackerConfigs.NUMBER_OF_ITEMS_TO_PICK_FROM) {
			throw new APIException(
					PackerConfigs.getProprtyValue("INVALID_ITEMS_COUNT", PackerConfigs.NUMBER_OF_ITEMS_TO_PICK_FROM));
		} else {
			pkg.getItems().forEach(item -> {
				if (item.getWeight() > PackerConfigs.MAX_WEIGHT_OF_ITEM
						|| item.getCost() > PackerConfigs.MAX_COST_OF_ITEM) {
					throw new APIException(
							PackerConfigs.getProprtyValue("INVALID_WEIGHT_COST", PackerConfigs.MAX_COST_OF_ITEM));
				}
			});
		}

	}

}
