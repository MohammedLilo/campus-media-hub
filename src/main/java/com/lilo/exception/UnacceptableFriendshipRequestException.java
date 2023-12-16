package com.lilo.exception;

@SuppressWarnings("serial")
public class UnacceptableFriendshipRequestException extends Exception {

	public UnacceptableFriendshipRequestException() {
		super();
	}

	public UnacceptableFriendshipRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnacceptableFriendshipRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnacceptableFriendshipRequestException(String message) {
		super(message);
	}

	public UnacceptableFriendshipRequestException(Throwable cause) {
		super(cause);
	}

}
