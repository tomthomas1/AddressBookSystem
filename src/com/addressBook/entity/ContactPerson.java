package com.addressBook.entity;

/**
 * In this class we are creating the contact and declaring the variables. We are
 * making the variables private and accessing it through getters and setters.
 * 
 * @author Tom
 *
 */
public class ContactPerson {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private long zip;
	private long phoneNumber;
	private String email;
	
	public ContactPerson() {
	}


	public ContactPerson(String firstName, String lastName, String address, String city, String state, long zip,
			long phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}





	/**
	 * Getters and setters
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * toString for the display pattern
	 */
	@Override
	public String toString() {
		
		return "First Name - "+firstName+", Last Name - "+lastName+", Phone Number - "+phoneNumber+", Email - "+email+", City - "+city+", State - "+state+", Zip Code - "+zip;
	}
}