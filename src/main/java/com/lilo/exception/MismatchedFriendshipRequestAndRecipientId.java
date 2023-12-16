package com.lilo.exception;

@SuppressWarnings("serial")
public class MismatchedFriendshipRequestAndRecipientId extends Exception {

	public MismatchedFriendshipRequestAndRecipientId() {
		super();
	}

	public MismatchedFriendshipRequestAndRecipientId(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MismatchedFriendshipRequestAndRecipientId(String message, Throwable cause) {
		super(message, cause);
	}

	public MismatchedFriendshipRequestAndRecipientId(String message) {
		super(message);
	}

	public MismatchedFriendshipRequestAndRecipientId(Throwable cause) {
		super(cause);
	}

}
