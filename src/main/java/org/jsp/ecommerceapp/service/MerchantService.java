package org.jsp.ecommerceapp.service;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructrue<Merchant>> save(Merchant merchant)
	{
		ResponseStructrue<Merchant> structrue=new ResponseStructrue<>();
		structrue.setBody(merchantDao.save(merchant));
		structrue.setMessage("Merchant Saved Successfully");
		structrue.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructrue<Merchant>>(structrue,HttpStatus.CREATED);
	}
}
