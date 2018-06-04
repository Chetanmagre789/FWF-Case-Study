package com.yash.tcvm.exception;

public class FileNameEmptyException extends RuntimeException {

	/**
	 * System generated serialVersionUID
	 */
	private static final long serialVersionUID = -3195184561504511629L;

	public FileNameEmptyException(String errorMessage) {
		super(errorMessage);
	}
	
}
