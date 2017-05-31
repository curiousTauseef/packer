package com.mobiquityinc.packer;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mobiquityinc.configs.PackerInjector;
import com.mobiquityinc.packer.parser.Parser;
import com.mobiquityinc.packer.pojos.Package;
import com.mobiquityinc.packer.processing.Packaging;

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

		Injector injector = Guice.createInjector(new PackerInjector());

		Parser parser = injector.getInstance(Parser.class);
		Packaging packaging = injector.getInstance(Packaging.class);

		// parse file
		List<Package> packages = parser.parseFile(absPath);
		// processing
		System.out.println(packaging.selectPackage(packages));
		return packaging.selectPackage(packages);
	}

	public static void main(String[] args) {
		pack("resources\\data.txt");
	}

}
