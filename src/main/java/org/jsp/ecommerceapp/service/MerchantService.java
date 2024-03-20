package org.jsp.ecommerceapp.service;

import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.exception.IdNotFindException;
import org.jsp.ecommerceapp.exception.InvalidCredentialException;
import org.jsp.ecommerceapp.exception.UserNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private EcommerceMailService emaMailService;
	
	public ResponseEntity<ResponseStructrue<Merchant>> save(Merchant merchant,HttpServletRequest request)
	{
		ResponseStructrue<Merchant> structrue=new ResponseStructrue<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structrue.setBody(merchantDao.save(merchant));
		String message=emaMailService.sendWelcomeEmail(merchant, request);
		structrue.setMessage("Merchant Saved Successfully" +"  "+message);
		structrue.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructrue<Merchant>>(structrue,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructrue<Merchant>> update(Merchant merchant)
	{
		Optional<Merchant> recMerchant=merchantDao.findById(merchant.getId());
		ResponseStructrue<Merchant> structrue=new ResponseStructrue<>();
		if(recMerchant.isPresent())
		{
			Merchant dbMerchant=recMerchant.get();
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			
			structrue.setBody(merchantDao.save(dbMerchant));
			structrue.setMessage("Merchant Updated Successfully...");
			structrue.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructrue<Merchant>>(structrue,HttpStatus.ACCEPTED);
		}
		
		throw new IdNotFindException("Id not Valid..");
	}
	
	public ResponseEntity<ResponseStructrue<Merchant>> verify(long phone,String password)
	{
		Optional<Merchant> recMerchant=merchantDao.verify(phone, password);
		ResponseStructrue<Merchant> structrue=new ResponseStructrue<>();
		if(recMerchant.isPresent())
		{
			Merchant m=recMerchant.get();
			if(m.getStatus().equals(AccountStatus.IN_ACTIVE.toString()))
			{
				throw new IllegalStateException("Account is in In_Active");
			}
			structrue.setMessage("Merchant Verified Successfully");
			structrue.setBody(recMerchant.get());
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<Merchant>>(structrue,HttpStatus.OK);
		}
		
		throw new InvalidCredentialException("Invalid Phone number and password");
	}
	
	public ResponseEntity<ResponseStructrue<Merchant>> verify(String email,String password)
	{
		Optional<Merchant> recMerchant=merchantDao.verify(email, password);
		ResponseStructrue<Merchant> structrue=new ResponseStructrue<>();
		if(recMerchant.isPresent())
		{
			Merchant m=recMerchant.get();
			if(m.getStatus().equals(AccountStatus.IN_ACTIVE.toString()))
			{
				throw new IllegalStateException("Account is in In_Active");
			}
			structrue.setMessage("Merchant Verified Successfully");
			structrue.setBody(recMerchant.get());
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<Merchant>>(structrue,HttpStatus.OK);
		}
		
		throw new InvalidCredentialException("Invalid email and password");
	}
	
	public ResponseEntity<ResponseStructrue<String>> findByToken(String token)
	{
		Optional<Merchant> recMerchant=merchantDao.activate(token);
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		if(recMerchant.isPresent())
		{
			Merchant merchant=recMerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantDao.save(merchant);
			structrue.setBody("Merchant Found");
			structrue.setMessage("Account Verified Successfully..");
			structrue.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.ACCEPTED);
		}
		
		throw new IdNotFindException("Invalid URL");
	}
	
	
}
