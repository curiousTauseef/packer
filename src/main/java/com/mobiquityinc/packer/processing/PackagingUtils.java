package com.mobiquityinc.packer.processing;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;

public abstract class PackagingUtils {

	private Integer index = 0;
	private Integer comparator = -1;

	/**
	 * 
	 * @param packages
	 *            {@link List} &nbsp; [list of packages to pick selected items]
	 * @return {@link String} &nbsp; [selected items in each package]
	 */
	protected String getNominatedItems(Package pkg) {
		StringBuilder selectedItems = new StringBuilder();

		if (pkg.getItems().isEmpty()) {
			selectedItems.append("-\n");
		} else if (pkg.getItems().size() == 1) {
			compareSingleItemParts(pkg, selectedItems);
		} else {
			compareMultipleItemsPart(pkg, selectedItems);
		}
		removeExtraComma(selectedItems);
		return selectedItems.toString();
	}

	protected void removeExtraComma(StringBuilder selectedItems) {
		index = selectedItems.lastIndexOf(",");
		if (index != comparator) {
			selectedItems.deleteCharAt(index);
		}
		selectedItems.append("\n");
		index = 0;
	}

	protected void compareMultipleItemsPart(Package pkg, StringBuilder selectedItems) {
		Double weightSum = 0.0;
		Double maxPackageWeight = pkg.getPackageMaximumWeight();

		for (Item item : pkg.getItems()) {
			weightSum += item.getWeight();

			if (weightSum <= maxPackageWeight) {
				selectedItems.append(item.getIndex() + ",");
			} else {
				weightSum -= item.getWeight();
			}

		}
	}

	protected void compareSingleItemParts(Package pkg, StringBuilder selectedItems) {
		Double maxPackageWeight = pkg.getPackageMaximumWeight();
		Item item = pkg.getItems().get(index);
		Double currentItemWeight = item.getWeight();
		if (currentItemWeight <= maxPackageWeight) {
			selectedItems.append(pkg.getItems().get(0).getIndex());
		} else {
			selectedItems.append("-");
		}
	}

	/**
	 * sort items desc according to price
	 * 
	 * @param items
	 *            {@link List} &nbsp; [unsorted list of items]
	 * @return {@link List} &nbsp; [sorted list of items]
	 */
	protected List<Item> sortItemAccordingToHighestPrice(List<Item> items) {

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
