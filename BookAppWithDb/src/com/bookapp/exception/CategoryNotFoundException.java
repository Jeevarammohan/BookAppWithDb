package com.bookapp.exception;

public class CategoryNotFoundException extends BookNotFoundException {

	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}
}
