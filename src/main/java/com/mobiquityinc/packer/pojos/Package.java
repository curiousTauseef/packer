package com.mobiquityinc.packer.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Holder for Package with all included items
 * 
 * @author Maha M. Hamza
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

	private double packageMaximumWeight;
	private List<Item> items;

}
