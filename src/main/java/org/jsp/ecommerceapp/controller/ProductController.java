package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value="/{id}")
	public ResponseEntity<ResponseStructrue<Product>> addProduct(@RequestBody Product product,@PathVariable(name="id") int id)
	{
		return productService.addProduct(product, id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructrue<Product>> update(@RequestBody Product product)
	{
		return productService.update(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructrue<List<Product>>> findAll()
	{
		return productService.findAll();
	}
}
