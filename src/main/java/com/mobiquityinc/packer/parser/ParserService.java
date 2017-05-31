package com.mobiquityinc.packer.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;
import com.mobiquityinc.packer.validators.Validator;

/**
 * This class include the packaging process implementation
 * 
 * @author Maha M. Hamza
 *
 */
@Singleton
public class ParserService implements Parser {

	@Inject
	public ParserService() {
	}

	/**
	 * input file parser and backing it into list of package
	 * 
	 * @return List<Package>
	 */
	@Override
	public List<Package> parseFile(String filePath) {
		Validator validator = Packer.injector.getInstance(Validator.class);
		List<Package> packages = new ArrayList<>();
		final Path path = Paths.get(filePath);
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(e -> packages.add(parsePackage(e)));
		} catch (IOException e) {
			throw new APIException(PARSING_EXCEPTION, e);
		}

		// validate input data
		packages.forEach(validator::validate);

		return packages;
	}

	/**
	 * 
	 * @param one
	 *            {@link String} &nbsp;[Single Package to parse]
	 * @return {@link Package} &nbsp; [parsed package into items and total
	 *         weight]
	 * @throws APIException
	 */
	private Package parsePackage(String one) throws APIException {
		Package pkg = new Package();
		try {
			String[] oneItems = one.split(":");
			// replacing "\\p{C}" cause of the non-printable Unicode character
			// that may cause input exception
			pkg.setPackageMaximumWeight(Double.parseDouble(oneItems[0].replaceAll(" ", "").replaceAll("\\p{C}", "")));

			List<Item> items = new ArrayList<>();
			// pattern to parse what between brackets ( and )
			Pattern pattern = Pattern.compile("\\((.*?)\\)");

			Matcher matcher = pattern.matcher(oneItems[1].replaceAll(" ", ""));
			while (matcher.find()) {
				String value = matcher.group().replace("(", "").replace(")", "");
				String[] finalValue = value.split(",");
				// item after parsing
				items.add(new Item(Integer.parseInt(finalValue[0]), Double.parseDouble(finalValue[1]),
						Double.parseDouble(finalValue[2].replace("€", ""))));
			} // end of while
			pkg.setItems(items);

		} catch (Exception e) {
			throw new APIException("Parsing Error in -> " + one, e);
		}
		return pkg;
	}

}
