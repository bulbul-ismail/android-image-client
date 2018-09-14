package com.ismail.imageservice.exception;

public class ImageNotFoundException extends ImageApiException {

	private static final long serialVersionUID = -8711382071514726858L;

	public ImageNotFoundException(String name) {
		super("Image with [name : " + name + "] not found");
	}

}
