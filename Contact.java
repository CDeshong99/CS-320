package org.snhu.cs320.contact;

import org.snhu.cs320.validation.Validation;

public class Contact {

	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	public Contact(String id, String firstName, String lastName, String phone, String address) throws Exception {
		super();
		
		if (id == null || id.trim().length() < 1 || id.length() > 10) {
			throw new Exception("id is bad");
		}
		
		this.id = id;

		if (firstName == null || firstName.trim().length() < 1 || firstName.length() > 10) {
			throw new Exception("firstName is bad");
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		if (phone == null || phone.trim().length() < 1 || phone.length() > 10 || phone.matches(".*\\D+.*")) {
			throw new Exception("phone is bad");
		}
		
		this.phone = phone;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception {
		Validation.validateNotNull(firstName, "firstName");
		Validation.validateNotBlank(firstName, "firstName");
		Validation.validateLength(firstName, "firstName", 1, 10);
		
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws Exception {
		Validation.validateNotNull(phone, "phone");
		Validation.validateNotBlank(phone, "phone");
		Validation.validateLength(phone, "phone", 1, 10);
		Validation.validateNumeric(phone, "phone");
		
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}
	
}
