package com.yash.tcvm.exception;

public class FileNotPresentException extends RuntimeException {

	/**
	 * System generated serialVersionUID
	 */
	private static final long serialVersionUID = -3531929660357110473L;

	public FileNotPresentException(String errorMessage) {
		super(errorMessage);
	}
}
