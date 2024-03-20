package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.Address;
import org.jsp.ecommerceapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<ResponseStructrue<Address>> save(@RequestBody Address address)
	{
		return addressService.save(address);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructrue<Address>> update(@RequestBody Address address)
	{
		return addressService.update(address);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructrue<List<Address>>> findAddress()
	{
		return addressService.findAddress();
	}
}
