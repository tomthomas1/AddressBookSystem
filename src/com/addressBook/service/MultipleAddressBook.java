package com.addressBook.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;

import com.addressBook.entity.ContactPerson;

/**
 * We have created this class to do the manipulations on the address Book.
 * [1] The method addAddressBook will add the address book key to the Map.
 * [2] The method editContact will edit the contacts in the address book.
 * [3]Method to search the address book by city.
 * [4]Method to search the address book by State.
 * [5] To display the contact by city or state
 * [6] Method to count the contacts in particular state or city.
 * [7] To display the directory.
 * @author Tom
 *
 */
public class MultipleAddressBook {
	public AddressBookServices addressBook;
	Scanner scannerObject = new Scanner(System.in);
	Map<String, AddressBookServices> addressBookMap = new HashMap<String, AddressBookServices>();

	/**
	 * We have created this class to call the functions of the address book 
	 */
	public void addressBookMain() {

		boolean moreChanges = true;
		do {

			System.out.println("\nChoose the operation on the Directory you want to perform");
			System.out.println(
					"1.Add an Address Book\n2.Edit Existing Address Book\n3.Search Person By Region\n4.View People By Region\n5.Count People By Region\n6.Display Address book Directory\n7.Exit Address book System");

			switch (scannerObject.nextInt()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				editAddressBook();
				break;
			case 3:
				System.out.println("Enter \n1.Search By City\n2.Search By State");
				int searChoice = scannerObject.nextInt();
				if (searChoice == 1)
					searchByCity();
				else
					searchByState();
				break;
			case 4:
				System.out.println("Enter \n1.Display By City\n2.Display By State");
				int displayChoice = scannerObject.nextInt();
				if (displayChoice == 1)
					displayPeopleByRegion(AddressBookServices.personByCity);
				else
					displayPeopleByRegion(AddressBookServices.personByState);
				break;
			case 5:
				System.out.println("Enter \n1.Display By City\n2.Display By State");
				int countChoice = scannerObject.nextInt();
				if (countChoice == 1)
					countPeopleByRegion(AddressBookServices.personByCity);
				else
					countPeopleByRegion(AddressBookServices.personByState);
				break;
			case 6:
				displayDirectoryContents();
				break;
			case 7:
				moreChanges = false;
				System.out.println("Exiting Address Book Directory !");
			}

		} while (moreChanges);
	}

	/**
	 * [1] The method addAddressBook will add the address book key to the Map.
	 * 1. We are taking a addressBook name from the console and using the .containsKep method to check if the book is already present
	 * 2. Else we will use the put method to add the key and value.
	 */
	public void addAddressBook() {

		System.out.println("Enter the name of the Address Book you want to add");
		String bookName = scannerObject.next();

		if (addressBookMap.containsKey(bookName)) {
			System.out.println("Book Name Already Exists");
			return;
		}
		AddressBookServices addressBook = new AddressBookServices();
		addressBook.setAddressBookName(bookName);
		addressBookMap.put(bookName, addressBook);

	}

	/**
	 * [2] The method editContact will edit the contacts in the address book
	 * 1. First we will ask the AddressBook name to edit the contact to.
	 * 2. Then we will get the key from the HashMap
	 * 3. We are then just calling the edit contact method
	 */
	public void editAddressBook() {

		System.out.println("Enter the Name of the Address Book which you want to edit:");
		String addressBookToEdit = scannerObject.next();

		if (addressBookMap.containsKey(addressBookToEdit)) {
			addressBook = addressBookMap.get(addressBookToEdit);
			addressBook.operation();
		} else {
			System.out.println("Book Does Not Exist");
		}

	}

	/**
	 * [3]Method to search the address book by city.
	 * 1. We will ask for the city and the person name to search
	 * 2. Then we will search in the list for the given city and name using the .equals method.
	 * 3. If found then we will display the contact.
	 */
	public void searchByCity() {

		System.out.println("Enter the name of the City where the Person resides : ");
		String cityName = scannerObject.next();
		System.out.println("Enter the name of the Person : ");
		String personName = scannerObject.next();

		for (AddressBookServices addressBook : addressBookMap.values()) {
			ArrayList<ContactPerson> contactList = addressBook.getContact();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getCity().equals(cityName))
					.forEach(person -> System.out.println(person));

		}
	}

	/**
	 * [4]Method to search the address book by State.
	 * 1. We will ask for the state and the person name to search
	 * 2. Then we will search in the list for the given state and name using the .equals method.
	 * 3. If found then we will display the contact.
	 */
	public void searchByState() {

		System.out.println("Enter the name of the State where the Person resides : ");
		String stateName = scannerObject.next();
		System.out.println("Enter the name of the Person : ");
		String personName = scannerObject.next();

		for (AddressBookServices addressBook : addressBookMap.values()) {
			ArrayList<ContactPerson> contactList = ((AddressBookServices) addressBook).getContact();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getState().equals(stateName))
					.forEach(person -> System.out.println(person));

		}

	}

	/**
	 * [5] To display the contact by city or state
	 * 1. We will get the city or state name
	 * 2. Here will pass the hashMap
	 * 3. We will use the filter method to get the contact with the given city or state.
	 * 4. Then we will display the contacts.
	 * @param listToDisplay
	 */
	public void displayPeopleByRegion(HashMap<String, ArrayList<ContactPerson>> listToDisplay) {

		System.out.println("Enter the name of the region :");
		String regionName = scannerObject.next();

		listToDisplay.values().stream()
				.map(region -> region.stream()
						.filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
				.forEach(person -> person.forEach(personDetails -> System.out.println(personDetails)));
	}

	/**
	 * [6] Method to count the contacts in particular state or city.
	 * We are using the count method to count the contacts after we filter it.
	 * @param listToDisplay
	 */
	public void countPeopleByRegion(HashMap<String, ArrayList<ContactPerson>> listToDisplay) {

		System.out.println("Enter the name of the region :");
		String regionName = scannerObject.next();

		long countPeople = listToDisplay.values().stream()
				.map(region -> region.stream()
						.filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName)))
				.count();

		System.out.println("Number of People residing in " + regionName + " are: " + countPeople + "\n");

	}

	/**
	 * [7] To display the directory.
	 * We are using the keySet to display the contact in the Map.
	 */
	public void displayDirectoryContents() {

		System.out.println("----- Contents of the Address Book Directory-----");
		for (String eachBookName : addressBookMap.keySet()) {

			System.out.println(eachBookName);
		}
		System.out.println("-----------------------------------------");
	}
}