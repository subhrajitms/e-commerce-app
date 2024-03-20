 package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant save(Merchant merchant)
	{
		return merchantRepository.save(merchant);
	}
	
	public Optional<Merchant> findById(int id)
	{
		return merchantRepository.findById(id);
	}
	
	public List<Merchant> findAll()
	{
		return merchantRepository.findAll();
	}
	
	public boolean deleteById(int id)
	{
		Optional<Merchant> recMerchant=merchantRepository.findById(id);
		if(recMerchant.isPresent())
		{
			merchantRepository.delete(recMerchant.get());
			return true;
		}
		return false;
	}
	
	public Optional<Merchant> verify(long phone,String passowrd)
	{
		return merchantRepository.verify(phone, passowrd);
	}
	
	public Optional<Merchant> verify(String email,String passowrd)
	{
		return merchantRepository.verify(email, passowrd);
	}
	
	public Optional<Merchant> activate(String token)
	{
		return merchantRepository.findByToken(token);
	}
	 
}
