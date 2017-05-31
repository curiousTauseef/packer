package com.mobiquityinc.configs;

/**
 * this interface will include all pre-defined values that will be used during
 * packaging life cycle<br/>
 * p.s. Singelton class
 * 
 * @author Maha M. Hamza
 *
 */
public interface StaticPreDefinedData {
	// Max weight that a package can take is ≤ 100
	Integer MAX_WEIGHT_OF_PACKAGE = 100;

	// Max weight and cost of an item is ≤ 100
	Integer MAX_WEIGHT_OF_ITEM = 100;
	Integer MAX_COST_OF_ITEM = 100;

	// There might be up to 15 items you need to choose from
	Integer NUMBER_OF_ITEMS_TO_PICK_FROM = 15;
}
