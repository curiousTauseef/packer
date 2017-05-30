package com.mobiquityinc.exception;

import java.nio.file.InvalidPathException;

/**
 * Exception class which is thrown if path is invalid one
 * 
 * @author Maha M. Hamza
 *
 */
public class PathValidationException extends InvalidPathException {

	private static final long serialVersionUID = 1L;

	public PathValidationException(String input, String reason) {
		super(input, reason);
	}

}
