package com.mobiquityinc.packer.processing;

import java.util.List;
import com.mobiquityinc.packer.pojos.Package;

/**
 * interface for packaging class
 * 
 * @author Maha M. Hamza
 *
 */
public interface Packaging {

	String pickAPackage(List<Package> packages);
}
