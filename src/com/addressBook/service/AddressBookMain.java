package com.addressBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.addressBook.entity.ContactPerson;
import com.jdbc.Statements;

/**
 * This is the main class and we are calling the main method there. We are
 * calling the addressBookMain in this method that has all the operations.
 * 
 * @author Tom
 *
 */
public class AddressBookMain {
	public static Scanner scannerObject = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("---------- Welcome To Address Book Pr1ogram!! ----------");
		MultipleAddressBook addressBookDirectory = new MultipleAddressBook();
		Statements st = new Statements();
		st.readAddressBook();
		addressBookDirectory.addressBookMain();
	}

}