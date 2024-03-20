package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Product;
import org.jsp.ecommerceapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
		
	public Product addProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}
	
	public Optional<Product> findById(int id)
	{
		return productRepository.findById(id);
	}
	
}
