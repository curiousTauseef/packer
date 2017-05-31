package com.mobiquityinc.packer.processing;

import java.util.List;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;

/**
 * Class that contain the packaging processing [picking suitable package items
 * to be included into package]
 * 
 * @author Maha M. Hamza
 *
 */
@Singleton
public class PackagingService extends PackagingUtils implements Packaging {

	@Inject
	public PackagingService() {
	}

	/**
	 * @param pkgs
	 *            {@link List} &nbsp; [packages to follow selective process]
	 * @return {@link String} &nbsp; [final output of the package selection
	 *         process]
	 */
	@Override
	public String selectPackage(List<Package> pkgs) {
		StringBuilder nominatedItems = new StringBuilder();
		// sorting packages according to heighest price
		pkgs.forEach(p -> {
			List<Item> sortedItems = sortItemAccordingToHighestPrice(p.getItems());
			Package pkg = new Package(p.getPackageMaximumWeight(), sortedItems);
			nominatedItems.append(getNominatedItems(pkg));
		});

		return nominatedItems.toString();
	}

}
