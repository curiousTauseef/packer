package com.mobiquityinc.packer.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class PackagingService implements Packaging {

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
	public String pickAPackage(List<Package> pkgs) {
		List<Package> packages = new ArrayList<>();
		// sorting packages according to heighest price
		pkgs.forEach(p -> {
			List<Item> sortedItems = sortItemAccordingToHighestPrice(p.getItems());
			packages.add(new Package(p.getPackageMaximumWeight(), sortedItems));
		});
		// processing
		return pickAPacket(packages);
	}

	/**
	 * 
	 * @param packages
	 *            {@link List} &nbsp; [list of packages to pick selected items]
	 * @return {@link String} &nbsp; [selected items in each package]
	 */
	private String pickAPacket(List<Package> packages) {
		String selectedItems = "";

		for (Package pkg : packages) {
			// use isempty
			if (pkg.getItems().size() == 0) {
				selectedItems += "-\n";
			} else if (pkg.getItems().size() == 1) {
				double maxPackageWeight = pkg.getPackageMaximumWeight();
				// magic number
				double currentItemWeight = pkg.getItems().get(0).getWeight();
				if (currentItemWeight <= maxPackageWeight) {
					selectedItems += pkg.getItems().get(0).getIndex();
				} else {
					selectedItems += "-";
				}
			} else {
				double weightSum = 0;
				double maxPackageWeight = pkg.getPackageMaximumWeight();

				for (Item item : pkg.getItems()) {
					weightSum += item.getWeight();

					if (weightSum <= maxPackageWeight) {
						selectedItems += item.getIndex() + ",";
					} else {
						weightSum -= item.getWeight();
					}

				}

			}
			// magic number , resolve it
			if (selectedItems.lastIndexOf(",") != -1)
				selectedItems = selectedItems.substring(0, selectedItems.lastIndexOf(","));
			selectedItems += "\n";
		}

		return selectedItems;
	}

	/**
	 * sort items desc according to price
	 * 
	 * @param items
	 *            {@link List} &nbsp; [unsorted list of items]
	 * @return {@link List} &nbsp; [sorted list of items]
	 */
	private List<Item> sortItemAccordingToHighestPrice(List<Item> items) {

		if (items.size() == 1) {
			return items;
		} else {
			// sorting array desc by cost
			Collections.sort(items,
					Comparator.comparingDouble(Item::getCost).reversed().thenComparingDouble(Item::getWeight));

		}
		return items;
	}

}
