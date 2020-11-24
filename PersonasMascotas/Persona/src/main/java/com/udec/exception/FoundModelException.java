package com.udec.exception;

public class FoundModelException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public FoundModelException (String mensaje) {
		super(mensaje);
	}

}
