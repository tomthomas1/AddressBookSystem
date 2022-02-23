package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	public static void main(String[] args) {

		System.out.println("---------Address Book---------");

		List<ContactPerson> contacts = new ArrayList<>();                                   // creating a ArrayList for storing the contacts

		ContactPerson newContact = new ContactPerson("Tom", "Thomas", "Dighi", "Pune", "Maharashtra", 411015,              
				"9158679678", "tomthomas@gmail.com");                                                                       // created a object

		contacts.add(newContact);                                                                                           //adding it to arrayList

		System.out.println(contacts);
	}

}
