package com.mobiquityinc.packer;

import java.util.List;

import com.mobiquityinc.configs.StaticPreDefined;
import com.mobiquityinc.packer.pojos.Package;

/**
 * 
 * Main class that start the whole packaging call
 * 
 * @author Maha M. Hamza
 *
 */
public class Packer {

	/**
	 * 
	 * @param absPath
	 *            {@link String} &nbsp; [ absolute path to the file which
	 *            contain data to be parsed]
	 * @return {@link String} &nbsp; [the output]
	 */
	public static String pack(String absPath) {
		// validate path , must terminate the program in case of failure
		StaticPreDefined.getValidator().validate(absPath);
		// parse file
		List<Package> packages = StaticPreDefined.getProcessing().parseFile(absPath);
		// processing
		return StaticPreDefined.getPackaging().pickAPackage(packages);
	}

}
