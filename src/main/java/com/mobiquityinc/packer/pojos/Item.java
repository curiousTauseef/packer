package com.mobiquityinc.packer.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Holder for single item
 * 
 * @author Maha M. Hamza
 *
 */

@Data
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Item {

	private int index;
	private double weight;
	private double cost;

}
