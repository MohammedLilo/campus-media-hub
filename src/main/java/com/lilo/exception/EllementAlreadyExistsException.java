package com.lilo.exception;

@SuppressWarnings("serial")
public class EllementAlreadyExistsException extends Exception {

	public EllementAlreadyExistsException() {
	}

	public EllementAlreadyExistsException(String message) {
		super(message);
	}

	public EllementAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public EllementAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EllementAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
