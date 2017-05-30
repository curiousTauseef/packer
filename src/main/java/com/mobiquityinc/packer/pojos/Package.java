package com.mobiquityinc.packer.pojos;

import java.util.List;

/**
 * Holder for Package with all included items
 * 
 * @author Maha M. Hamza
 *
 */
public class Package {

	private double packageMaximumWeight;
	private List<Item> items;

	public Package() {
	}

	public Package(double packageMaximumWeight, List<Item> items) {
		super();
		this.packageMaximumWeight = packageMaximumWeight;
		this.items = items;
	}

	public void setPackageMaximumWeight(double packageMaximumWeight) {
		this.packageMaximumWeight = packageMaximumWeight;
	}

	public double getPackageMaximumWeight() {
		return packageMaximumWeight;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}
}
