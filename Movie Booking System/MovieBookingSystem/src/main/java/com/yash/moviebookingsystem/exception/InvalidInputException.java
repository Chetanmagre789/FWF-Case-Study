package com.yash.moviebookingsystem.exception;

public class InvalidInputException extends RuntimeException {

	/**
	 * System generated serialVersionUID
	 */
	private static final long serialVersionUID = -8039962396107125854L;

	public InvalidInputException(String errorMesaage) {
		super(errorMesaage);
	}
}
