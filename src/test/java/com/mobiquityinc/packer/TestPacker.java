package com.mobiquityinc.packer;

import java.io.File;
import java.io.IOException;
import static com.mobiquityinc.CustomAssert.assertObject;
import junit.framework.TestCase;

/**
 * 
 * Test case for Packaging Process
 * 
 * @author Maha M. Hamza
 *
 */
public class TestPacker extends TestCase {

	/**
	 * Test main function of Packer output
	 * 
	 * @throws IOException
	 */
	public void testPack() throws IOException {

		File file = new File("resources\\data.txt");

		String absolutePath = file.getCanonicalPath();

		String result = "4\n-\n2,7\n8,9\n";

		assertObject(result, Packer.pack(absolutePath));
	}

}
