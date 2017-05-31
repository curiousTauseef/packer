package com.mobiquityinc.configs;

import com.google.inject.AbstractModule;
import com.mobiquityinc.packer.parser.Parser;
import com.mobiquityinc.packer.parser.ParserService;
import com.mobiquityinc.packer.processing.Packaging;
import com.mobiquityinc.packer.processing.PackagingService;
import com.mobiquityinc.packer.validators.ValidatorService;

public class PackerInjector extends AbstractModule {

	@Override
	protected void configure() {

		bind(Packaging.class).to(PackagingService.class);
		bind(Parser.class).to(ParserService.class);
		bind(ValidatorService.class);

	}

}
