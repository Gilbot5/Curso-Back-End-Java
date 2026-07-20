package com.techlab.ecommerce.entrega.exception;

public class ExceptionHandler extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExceptionHandler(String mensaje) { 
		super(mensaje);
	}

}