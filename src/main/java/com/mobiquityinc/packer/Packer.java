package com.mobiquityinc.packer;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mobiquityinc.configs.PackerInjector;
import com.mobiquityinc.packer.parser.Parser;
import com.mobiquityinc.packer.pojos.Package;
import com.mobiquityinc.packer.processing.Packaging;
import com.mobiquityinc.packer.validators.Validator;

/**
 * 
 * Main class that start the whole packaging call
 * 
 * @author Maha M. Hamza
 *
 */
public class Packer {

	public static Injector injector = Guice.createInjector(new PackerInjector());

	/**
	 * 
	 * @param absPath
	 *            {@link String} &nbsp; [ absolute path to the file which
	 *            contain data to be parsed]
	 * @return {@link String} &nbsp; [the output]
	 */
	public static String pack(String absPath) {
		Validator validator = injector.getInstance(Validator.class);
		Parser parser = injector.getInstance(Parser.class);
		Packaging packaging = injector.getInstance(Packaging.class);
		// validate path , must terminate the program in case of failure
		validator.validate(absPath);
		// parse file
		List<Package> packages = parser.parseFile(absPath);
		// processing
		return packaging.pickAPackage(packages);
	}

}
