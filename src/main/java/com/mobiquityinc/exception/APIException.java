package com.mobiquityinc.exception;

/**
 * Custom Exception
 * 
 * 
 * 
 * extends <strong>IllegalArgumentException</strong> According to java 7
 * <i>"Thrown to indicate that a method has been passed an illegal or
 * inappropriate argument"</li>
 * 
 * @see https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html
 * @author Maha M. Hamza
 */
public class APIException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	// developer written text to express the exception
	private String msg;
	// thrown exception of type IllegalArgumentException
	private Exception e;

	public APIException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public APIException(String msg, Exception e) {
		super(msg, e);
		this.msg = msg;
		this.e = e;
	}

	public String getMsg() {
		return msg;
	}

	public Exception getE() {
		return e;
	}
}
