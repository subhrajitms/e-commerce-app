package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.AddressDao;
import org.jsp.ecommerceapp.dto.ResponseStructrue;
import org.jsp.ecommerceapp.exception.AddressNotFoundException;
import org.jsp.ecommerceapp.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<ResponseStructrue<Address>> save(Address address)
	{
		ResponseStructrue<Address> structrue=new ResponseStructrue<>();
		structrue.setBody(addressDao.saveAddress(address));
		structrue.setMessage("Address Addedd..");
		structrue.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructrue<Address>>(structrue,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructrue<Address>> update(Address address)
	{
		Optional<Address> recAddress=addressDao.findById(address.getId());
		ResponseStructrue<Address> structrue=new ResponseStructrue<>();
		if(recAddress.isPresent())
		{
			Address dbAdd=recAddress.get();
			dbAdd.setLandmark(address.getLandmark());
			dbAdd.setBuildingName(address.getBuildingName());
			dbAdd.setHouseNumber(address.getHouseNumber());
			dbAdd.setAddressType(address.getAddressType());
			dbAdd.setCity(address.getCity());
			dbAdd.setState(address.getState());
			dbAdd.setCountry(address.getCountry());
			dbAdd.setPincode(address.getPincode());
			
			structrue.setBody(addressDao.saveAddress(dbAdd));
			structrue.setMessage("Address Updated ..");
			structrue.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructrue<Address>>(structrue,HttpStatus.ACCEPTED);
		}
		throw new AddressNotFoundException("Address Not Found");
		
	}
	public ResponseEntity<ResponseStructrue<List<Address>>>findAddress()
	{
		List<Address> recAddress=addressDao.findAddress();
		ResponseStructrue<List<Address>> structrue=new ResponseStructrue<>();
		if(recAddress.size()>0)
		{
		   structrue.setBody(recAddress);
		   structrue.setMessage("Address Found..");
		   structrue.setStatuscode(HttpStatus.OK.value());
		}
		
		throw new AddressNotFoundException("Address Not Found");
	}
}
