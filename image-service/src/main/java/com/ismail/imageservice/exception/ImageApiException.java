package com.ismail.imageservice.exception;

public class ImageApiException extends RuntimeException {

	private static final long serialVersionUID = -8380681020973125180L;

	public ImageApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageApiException(String message) {
		super(message);
	}

}
