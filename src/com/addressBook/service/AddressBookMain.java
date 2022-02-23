package com.addressBook.service;

import java.util.ArrayList;
import java.util.List;
import com.addressBook.entity.ContactPerson;

public class AddressBookMain {
	public static void main(String[] args) {

		System.out.println("---------Address Book---------");

		List<ContactPerson> contacts = new ArrayList<>();                                //ArrayList
		AddressBookServices addnew = new AddressBookServices();                          //object to access the class methods

		ContactPerson newContact = new ContactPerson("Tom", "Thomas", "Dighi", "Pune", "Maharashtra", 411015, 999997899,"tomthomas@gmail.com");

		contacts.add(newContact);                                                //to add the hardcoded value
		addnew.addContact(contacts);                                             //to call the add new function to add contact

		System.out.println(contacts);

	}
}