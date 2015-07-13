package com.test.exceptions;

public class RunDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RunDoesNotExistsException(String buildNumber) {
        super("Book '" + buildNumber + "' already exists.");
      }
}
