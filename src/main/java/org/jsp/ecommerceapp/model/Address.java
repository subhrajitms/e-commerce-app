package org.jsp.ecommerceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String landmark;
	@Column(nullable = false)
	private String buildingName;
	private int houseNumber;
	private String addressType;
	private String city;
	private String state;
	private String country;
	private int pincode;
}
