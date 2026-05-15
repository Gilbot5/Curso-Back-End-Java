package com.techlab.ecommerce.exception;

public class ProductoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductoException(String mensaje) { 
		super(mensaje); 
	}
}