package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}
	
	public Optional<User> findById(int id)
	{
		return userRepository.findById(id);
	}
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	
	public boolean delete(int id)
	{
		Optional<User> recUser=userRepository.findById(id);
		if(recUser.isPresent())
		{
			userRepository.delete(recUser.get());
			return true;
		}
		return false;
	}
	
	public Optional<User> verify(long phone,String password)
	{
		return userRepository.verify(phone, password);
	}
	
	public Optional<User> verify(String email,String password)
	{
		return userRepository.verify(email, password);
	}
	
	public Optional<User> activate(String token)
	{
		return userRepository.findByToken(token);
	}
}
