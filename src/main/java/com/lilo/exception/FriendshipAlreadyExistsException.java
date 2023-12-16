package com.lilo.exception;

@SuppressWarnings("serial")
public class FriendshipAlreadyExistsException extends Exception {

	public FriendshipAlreadyExistsException() {
	}

	public FriendshipAlreadyExistsException(String message) {
		super(message);
	}

	public FriendshipAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public FriendshipAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public FriendshipAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
