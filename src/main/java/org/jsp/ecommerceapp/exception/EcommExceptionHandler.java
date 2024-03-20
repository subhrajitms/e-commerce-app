package org.jsp.ecommerceapp.exception;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EcommExceptionHandler {
	
	@ExceptionHandler(IdNotFindException.class)
	public ResponseEntity<ResponseStructrue<String>> handleIDNF(IdNotFindException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Invalid Id...");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructrue<String>> handleUNE(UserNotFoundException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Invalid Id...");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ResponseStructrue<String>> handleICF(InvalidCredentialException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Invalid Credentials...");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStructrue<String>> handlePNF(ProductNotFoundException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Invalid Credentials...");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ResponseStructrue<String>> handleINF(IllegalStateException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Account Not Activated");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructrue<String>> handleINF(AddressNotFoundException exception)
	{
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		structrue.setMessage("Address Not Found...");
		structrue.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.NOT_FOUND);
	}
	
	
	
}
