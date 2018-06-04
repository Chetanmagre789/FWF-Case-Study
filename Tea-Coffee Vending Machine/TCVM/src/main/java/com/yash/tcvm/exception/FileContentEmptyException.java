package com.yash.tcvm.exception;

public class FileContentEmptyException extends RuntimeException {

	/**
	 * System generated serialVersionUID
	 */
	private static final long serialVersionUID = -4446199971225418671L;

	public FileContentEmptyException(String errorMessage) {
		super(errorMessage);
	}

}
