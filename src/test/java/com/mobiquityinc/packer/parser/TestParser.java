package com.mobiquityinc.packer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.configs.StaticPreDefined;
import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;
import static com.mobiquityinc.CustomAssert.assertObject;
import junit.framework.TestCase;

public class TestParser extends TestCase {

	public void testParseFile() throws IOException {
		File file = new File("resources\\data.txt");

		String absolutePath = file.getCanonicalPath();

		List<Package> packages = StaticPreDefined.getProcessing().parseFile(absolutePath);

		Package pkg = new Package();
		pkg.setPackageMaximumWeight(81.0);
		List<Item> items = new ArrayList<>();
		items.add(new Item(1, 53.38, 45.0));
		items.add(new Item(2, 88.62, 98.0));
		items.add(new Item(3, 78.48, 3.0));
		items.add(new Item(4, 72.3, 76.0));
		items.add(new Item(5, 30.18, 9.0));
		items.add(new Item(6, 46.34, 48.0));
		pkg.setItems(items);

		assertObject(new Item(0, 0, 0), packages.get(0));

	}

}
