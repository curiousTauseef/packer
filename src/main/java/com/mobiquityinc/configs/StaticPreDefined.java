package com.mobiquityinc.configs;

import com.mobiquityinc.packer.parser.IParser;
import com.mobiquityinc.packer.parser.Parser;
import com.mobiquityinc.packer.processing.IPrackaging;
import com.mobiquityinc.packer.processing.Packaging;
import com.mobiquityinc.packer.validators.IValidator;
import com.mobiquityinc.packer.validators.Validator;

/**
 * this class will include all pre-defined values that will be used during
 * packaging life cycle<br/>
 * p.s. Singelton class
 * 
 * @author Maha M. Hamza
 *
 */
public class StaticPreDefined {

	// Max weight that a package can take is ≤ 100
	public static int MAX_WEIGHT_OF_PACKAGE = 100;

	// Max weight and cost of an item is ≤ 100
	public static int MAX_WEIGHT_OF_ITEM = 100;
	public static int MAX_COST_OF_ITEM = 100;

	// There might be up to 15 items you need to choose from
	public static int NUMBER_OF_ITEMS_TO_PICK_FROM = 15;

	// objects to be used in the whole application
	private static IValidator validator = new Validator();
	private static IParser processing = new Parser();
	private static IPrackaging packaging = new Packaging();

	private StaticPreDefined() {
	}

	public static IValidator getValidator() {
		return validator;
	}

	public static IParser getProcessing() {
		return processing;
	}

	public static IPrackaging getPackaging() {
		return packaging;
	}
}
