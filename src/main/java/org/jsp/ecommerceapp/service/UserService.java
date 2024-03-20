package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.exception.IdNotFindException;
import org.jsp.ecommerceapp.exception.InvalidCredentialException;
import org.jsp.ecommerceapp.exception.UserNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMailService emaMailService;
	
	public ResponseEntity<ResponseStructrue<User>> save(User user,HttpServletRequest request)
	{
		ResponseStructrue<User> structrue=new ResponseStructrue<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		structrue.setBody(userDao.saveUser(user));
		String message=emaMailService.sendWelcomeMail(user, request);
		structrue.setMessage("User Saved Successfully:"+ message);
		structrue.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructrue<User>>(structrue,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructrue<User>> update(User user)
	{
	   Optional<User> recUser=userDao.findById(user.getId());
	   ResponseStructrue<User> structrue=new ResponseStructrue<>();
	   if(recUser.isPresent())
	   {
		   User userDb=recUser.get();
		   userDb.setName(user.getName());
		   userDb.setEmail(user.getEmail());
		   userDb.setPhone(user.getPhone());
		   userDb.setAge(user.getAge());
		   userDb.setGender(user.getGender());
		   userDb.setPassword(user.getPassword());
		   structrue.setBody(userDao.saveUser(userDb));
		   structrue.setMessage("User Updated Successfully...");
		   structrue.setStatuscode(HttpStatus.ACCEPTED.value());
		   
		   return new ResponseEntity<ResponseStructrue<User>>(structrue,HttpStatus.ACCEPTED);
	   }
	   
	   throw new IdNotFindException("Invalid Id..");
	}
	
	public ResponseEntity<ResponseStructrue<User>> findById(int id)
	{
		Optional<User> recUser=userDao.findById(id);
		ResponseStructrue<User> structrue=new ResponseStructrue<>();
		if(recUser.isPresent())
		{
			structrue.setBody(recUser.get());
			structrue.setMessage("User Find Successfully..");
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<User>>(structrue,HttpStatus.OK);
		}
		
		throw new IdNotFindException("Invalid Id..");
	}
	
	public ResponseEntity<ResponseStructrue<List<User>>> findAll()
	{
		List<User> recUser=userDao.findAll();
		ResponseStructrue<List<User>> structrue=new ResponseStructrue<>();
		if(recUser.size()>0)
		{
			structrue.setBody(recUser);
			structrue.setMessage("User Find Successfully..");
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<List<User>>>(structrue,HttpStatus.OK);
		}
		
		throw new UserNotFoundException("No User Found..");
	}
	
	public ResponseEntity<ResponseStructrue<String>> delete(int id)
	{
		boolean recUser=userDao.delete(id);
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		if(recUser)
		{
	       structrue.setBody("User deleted Successfully..");
	       structrue.setMessage("deleted");
	       structrue.setStatuscode(HttpStatus.OK.value());
	       return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.OK);
	       
		}
		throw new IdNotFindException("Invalid Id..");
	}
	
	public ResponseEntity<ResponseStructrue<User>> verify(long phone,String password)
	{
		Optional<User> recUser=userDao.verify(phone, password);
		ResponseStructrue<User> structrue=new ResponseStructrue<>();
		if(recUser.isPresent())
		{
			structrue.setMessage("User Verification Successfull..");
			structrue.setBody(recUser.get());
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<User>>(structrue,HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid User phone and password");
	}
	
	public ResponseEntity<ResponseStructrue<User>> verify(String email,String password)
	{
		Optional<User> recUser=userDao.verify(email, password);
		ResponseStructrue<User> structrue=new ResponseStructrue<>();
		if(recUser.isPresent())
		{
			structrue.setMessage("User Verification Successfull..");
			structrue.setBody(recUser.get());
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<User>>(structrue,HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid email and password");
	}
	
	public ResponseEntity<ResponseStructrue<String>> activate(String token)
	{
		Optional<User> recUser=userDao.activate(token);
		ResponseStructrue<String> structrue=new ResponseStructrue<>();
		if(recUser.isPresent())
		{
			User user=recUser.get();
			user.setStatus(AccountStatus.ACTIVE.toString());
			user.setToken(null);
			userDao.saveUser(user);
			structrue.setBody("User Found");
			structrue.setMessage("Account Verified Successfully..");
			structrue.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructrue<String>>(structrue,HttpStatus.ACCEPTED);
		}
		
		throw new IdNotFindException("Invalid URL");
	}
	
	
}
