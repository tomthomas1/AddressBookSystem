package com.addressBook.service;

import java.util.List;
import java.util.Scanner;

import com.addressBook.entity.ContactPerson;

public class AddressBookServices {
	Scanner sc = new Scanner(System.in);

	public void addContact(List<ContactPerson> contacts) {

		Scanner scan = new Scanner(System.in);
		System.out.print(" Please enter the first name: ");
		String firstName = scan.next();

		System.out.print(" Please enter the last name: ");
		String lastName = scan.next();

		System.out.print(" Please enter the address: ");
		String address = scan.next();

		System.out.print(" Please enter the city: ");
		String city = scan.next();

		System.out.print(" Please enter the state: ");
		String state = scan.next();

		System.out.print(" Please enter the zip: ");
		int zip = scan.nextInt();

		System.out.print(" Please enter the phone number: ");
		Long phoneNumber = scan.nextLong();

		System.out.print(" Please enter the email: ");
		String email = scan.next();
 
		//taking the input from console and then adding it to the ArrayList contacts
		ContactPerson newContact = new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber,
				email);
		contacts.add(newContact);

	}

}
