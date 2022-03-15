package com.addressBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.addressBook.entity.ContactPerson;
/**
 *  This is the main function and we are calling the methods in this class.
 * @author Tom
 *
 */
public class AddressBookMain {
	public static void main(String[] args) {

		System.out.println("---------Address Book---------");
		Scanner sc = new Scanner(System.in);

		/**
		 * We have created the objects of the class.
		 */
		MultipleAddressBook obj = new MultipleAddressBook(); 
		AddressBookServices add = new AddressBookServices();


		/**
		 * While loop to call the methods will the scanner is closed.
		 */
		while (true) {
			System.out.println("Enter \n 1. To add the new AddressBook\n 2. To add contact in AddressBook\n "
					+ "3. To edit the contact in AddressBook\n 4. To delete the contact in AddressBook\n 5. To delete the AddressBook\n "
					+ "6. To Print the AddressBook\n 7. To Print the contacts in AddressBook\n 8. Search Person By City. \n 9. Search Person by State \n 0. To exit");
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
			case 8:
				obj.searchByCity();;
				break;
			case 9:
				obj.searchByState();;
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