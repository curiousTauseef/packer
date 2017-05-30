package com.mobiquityinc;

import java.util.List;
import java.util.stream.IntStream;

import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;

import junit.framework.TestCase;

public class CustomAssert extends TestCase {

	public static void assertObject(Object expected, Object actual) {

		if (expected.getClass() == Package.class && actual.getClass() == Package.class) {
			Package exp = (Package) expected;
			Package act = (Package) actual;
			assertEquals(exp.getPackageMaximumWeight(), act.getPackageMaximumWeight());
			assertEquals(exp.getItems().size(), act.getItems().size());
			if (exp.getItems().size() > 0) {
				List<Item> items1 = exp.getItems();
				List<Item> items2 = act.getItems();
				IntStream.range(0, items1.size())
						.forEach(i -> assertEquals(items1.get(i).toString(), items2.get(i).toString()));
			}

		} else if (expected.getClass() == String.class && actual.getClass() == String.class) {
			assertEquals(expected, actual);
		} else if (expected.getClass() != actual.getClass()) {
			assertFalse("Expected object type is not the same as Actual object type", true);
		} else {
			assertFalse("Expected/Actual object type if undefined", true);
		}
	}
}
