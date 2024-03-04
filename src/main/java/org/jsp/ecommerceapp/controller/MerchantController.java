package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/merchants")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	@PostMapping
	public ResponseEntity<ResponseStructrue<Merchant>> save(@RequestBody Merchant merchant)
	{
	  return merchantService.save(merchant);
	}
}
