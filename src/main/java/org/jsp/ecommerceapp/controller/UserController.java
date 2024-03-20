package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructrue<User>> save(@RequestBody User user,HttpServletRequest request)
	{
		return userService.save(user,request);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructrue<User>> update(@RequestBody User user)
	{
		return userService.update(user);
	}
	
	@GetMapping(value="/find-by-id/{id}")
	public ResponseEntity<ResponseStructrue<User>> findById(@PathVariable(name="id") int id)
	{
		return userService.findById(id);
	}
	
	@GetMapping(value="/find-all-user")
	public ResponseEntity<ResponseStructrue<List<User>>> findAll()
	{
		return userService.findAll();
	}
	
	@DeleteMapping(value="delete-user/{id}")
	public ResponseEntity<ResponseStructrue<String>> delete(@PathVariable(name="id") int id)
	{
		return userService.delete(id);
	}
	
	@PostMapping(value="/verify-by-phone")
	public ResponseEntity<ResponseStructrue<User>> verifyByPhone(@RequestParam long phone,@RequestParam String password)
	{
		return userService.verify(phone, password);
	}
	
	@PostMapping(value="/verify-by-email")
	public ResponseEntity<ResponseStructrue<User>> verifyByEmail(@RequestParam String email,@RequestParam String password)
	{
		return userService.verify(email, password);
	}
	
	@GetMapping("/activate")
	public ResponseEntity<ResponseStructrue<String>> activate(@RequestParam String token)
	{
		return userService.activate(token);
	}
}
