package com.mobiquityinc.packer.parser;

import java.util.List;

import com.mobiquityinc.packer.pojos.Package;

/**
 * Interface for file parsers
 * 
 * @author Maha M. Hamza
 *
 */
public interface Parser {

	List<Package> parseFile(String filePath);

}
