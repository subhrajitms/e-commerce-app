package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dao.ProductDao;
import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.exception.ProductNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private MerchantDao merchantDao;
	
	
	public ResponseEntity<ResponseStructrue<Product>> addProduct(Product product,int id)
	{
		Optional<Merchant> recMerchant=merchantDao.findById(id);
		ResponseStructrue<Product> structrue=new ResponseStructrue<>();
		if(recMerchant.isPresent())
		{
			Merchant merchant=recMerchant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			structrue.setBody(productDao.addProduct(product));
			structrue.setMessage("Product Added Successfully");
			structrue.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructrue<Product>>(structrue,HttpStatus.CREATED);
		}
		
		throw new ProductNotFoundException("Merchant not Found");
	}
	
	public ResponseEntity<ResponseStructrue<Product>> update(Product product)
	{
		Optional<Product> recProduct=productDao.findById(product.getId());
		ResponseStructrue<Product> structrue=new ResponseStructrue<>();
		if(recProduct.isPresent())
		{
			Product dbProduct=recProduct.get();
			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			dbProduct.setImage_url(product.getImage_url());
			
			structrue.setBody(productDao.addProduct(dbProduct));
			structrue.setMessage("Product Updated Successfully..");
			structrue.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructrue<Product>>(structrue,HttpStatus.ACCEPTED);
		}
		
		throw new ProductNotFoundException("product Id not Found");
		
	}
	
	public ResponseEntity<ResponseStructrue<List<Product>>> findAll()
	{
		List<Product> recProduct=productDao.findAll();
		ResponseStructrue<List<Product>> structrue=new ResponseStructrue<>();
		if(recProduct.size()>0)
		{
			structrue.setMessage("Product Found Successfully..");
			structrue.setBody(recProduct);
			structrue.setStatuscode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructrue<List<Product>>>(structrue,HttpStatus.OK);
		}
		
	   throw new ProductNotFoundException("Product not Found");
	}
}
