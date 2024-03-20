package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value="/merchants")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	@PostMapping
	public ResponseEntity<ResponseStructrue<Merchant>> save(@RequestBody Merchant merchant,HttpServletRequest request)
	{
	  return merchantService.save(merchant,request);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructrue<Merchant>> update(@RequestBody Merchant merchant)
	{
		return merchantService.update(merchant);
	}
	
	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructrue<Merchant>> verify(@RequestParam long phone,@RequestParam String password)
	{
		return merchantService.verify(phone, password);
	}
	
	@PostMapping("/verifyemail")
	public ResponseEntity<ResponseStructrue<Merchant>> verify(@RequestParam String email,@RequestParam String password)
	{
		return merchantService.verify(email, password);
	}
	
	@GetMapping("/activate")
	public ResponseEntity<ResponseStructrue<String>> activate(@RequestParam String tokens)
	{
		return merchantService.findByToken(tokens);
	}
	
	
}
