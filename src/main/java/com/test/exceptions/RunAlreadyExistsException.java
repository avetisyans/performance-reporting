package com.test.exceptions;


public class RunAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RunAlreadyExistsException(String buildNumber) {
        super("Book '" + buildNumber + "' already exists.");
      }
}
