package com.bookapp.exception;

public class AuthorNotFoundException extends BookNotFoundException {

	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException() {
		super();
	}

	public AuthorNotFoundException(String message) {
		super(message);
	}

}
