package com.lilo.exception;

@SuppressWarnings("serial")
public class GroupMembershipAlreadyExistsException extends Exception {

	public GroupMembershipAlreadyExistsException() {
	}

	public GroupMembershipAlreadyExistsException(String message) {
		super(message);
	}

	public GroupMembershipAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public GroupMembershipAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupMembershipAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
