package org.jsp.ecommerceapp.exception;

public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException(String msg)
	{
		super(msg);
	}
}
