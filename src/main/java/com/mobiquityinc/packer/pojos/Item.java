package com.mobiquityinc.packer.pojos;

/**
 * Holder for single item
 * 
 * @author Maha M. Hamza
 *
 */
public class Item {

	private int index;
	private double weight;
	private double cost;

	public Item(int index, double weight, double cost) {
		super();
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Item [index=" + index + ", weight=" + weight + ", cost=" + cost + "]";
	}

}
