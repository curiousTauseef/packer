package com.mobiquityinc.packer.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mobiquityinc.configs.PackerConfigs;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.pojos.Item;
import com.mobiquityinc.packer.pojos.Package;
import com.mobiquityinc.packer.validators.ValidatorService;

/**
 * This class include the packaging process implementation
 * 
 * @author Maha M. Hamza
 *
 */
@Singleton
public class ParserService implements Parser {

	@Inject
	Injector injector;

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
		ValidatorService validator = injector.getInstance(ValidatorService.class);
		List<Package> packages = new ArrayList<>();
		final Path path = Paths.get(filePath);
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(e -> packages.add(parsePackage(e)));
		} catch (IOException e) {
			throw new APIException(PackerConfigs.getProprtyValue("PARSING_EXCEPTION"), e);
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
			String[] item = one.split(":");

			// replacing "\\p{C}" cause of the non-printable Unicode character
			// that may cause input exception
			pkg.setPackageMaximumWeight(Double.parseDouble(item[0].replaceAll("\\s\\p{C}", "")));

			List<Item> items = Arrays.asList(item[1].trim().split(" ")).stream().map(itemString -> {
				String[] itemDetails = itemString.split(",");
				Integer index = Integer.parseInt(itemDetails[0].substring(1));
				Double weight = Double.parseDouble(itemDetails[1]);
				Double price = Double.parseDouble(itemDetails[2].substring(1, itemDetails[2].length() - 1));
				return new Item(index, weight, price);
			}).collect(Collectors.toList());

			pkg.setItems(items);

		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException("Parsing Error in -> " + one, e);
		}
		return pkg;
	}

}
