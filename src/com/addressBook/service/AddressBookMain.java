package com.addressBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.addressBook.entity.ContactPerson;

public class AddressBookMain {
	public static void main(String[] args) {

		System.out.println("---------Address Book---------");
		Scanner sc = new Scanner(System.in);

		List<ContactPerson> contacts = new ArrayList<ContactPerson>(); // ArrayList
		AddressBookServices addnew = new AddressBookServices();
		MultipleAddressBook obj = new MultipleAddressBook(); // object to access the class methods


		while (true) {
			System.out.println("Enter \n 1. To add the new AddressBook\n 2. To add contact in AddressBook\n "
					+ "3. To edit the contact in AddressBook\n 4. To delete the contact in AddressBook\n 5. To delete the AddressBook\n "
					+ "6. To Print the AddressBook\n 7. To Print the contacts in AddressBook\n 0. To exit");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				obj.addAddressBook();
				break;
			case 2:
				obj.addContact();
				break;
			case 3:
				obj.editContactInBook();
				break;
			case 4:
				obj.deleteContactInBook();
				break;
			case 5:
				obj.deleteAddressBook();
				break;
			case 6:
				obj.printBook();
				break;
			case 7:
				obj.printContactsInBook();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("Enter the wrong input");
			}
		}
	}
}