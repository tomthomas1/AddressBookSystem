package com.addressBook.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import com.addressBook.entity.ContactPerson;

/**
 * In this class we are creating methods for the contact manipulation. This
 * class has methods related to specific contacts. 
 * [1] This method is to take the input from console and set the values of the contact.
 * [2] The editContact method will edit the contact in the list.
 * [3] Method delete to delete a specific contact.
 * [4] Method to add the contact person to city list.
 * [5] Method to add the contact person to State list.
 * [6] Method to sort address Book by firstname/state/ city/ zip
 * [7] Method to print the sorted address Book
 * [8] Method to write the data to file
 * [9] Method to read the data from the file and display in console.
 * [10] Method to write the data to a CSV file
 * [11] Method to read data from a CSV file
 * 
 * @author Tom
 *
 */
public class AddressBookServices {
	Scanner scannerObject = new Scanner(System.in);
	/**
	 * We have used a HashMap to save the contacts and the addressBook name.
	 */
	public Map<String, ContactPerson> contact = new HashMap<String, ContactPerson>();
	public static HashMap<String, ArrayList<ContactPerson>> personByCity = new HashMap<String, ArrayList<ContactPerson>>();
	public static HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String, ArrayList<ContactPerson>>();
	public String addressBookName;
	public boolean isPresent = false;

	/**
	 * We are using this method to get the addressBook name
	 * @return - It will return the address Book name
	 */
	public String getAddressBookName() {
		return addressBookName;
	}
	
	public enum IOService {
		CONSOLE_IO, FILE_IO
	}

	/**
	 * It is used to set the address Book name
	 * @param addressBookName - We will pass the name we want to give to address
	 *                        book
	 */
	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	/**
	 * We are using this method to get the contact in the list.
	 * @return - the list of contacts
	 */
	public ArrayList<ContactPerson> getContact() {
		return new ArrayList<ContactPerson>(contact.values());
	}

	/**
	 * We have created this method to perform the various operations on contacts. We
	 * have used the do while loop to iterate.
	 */
	public void operation() {

		boolean moreChanges = true;
		do {

			System.out.println("\nChoose the operation you want to perform");
			System.out.println(
					"1.Add Contact to Address Book\n2.Edit Existing contact\n3.Display contact book\n4.Delete Contact\n5.Display Sorted Address Book \n6.Write To File\n7.Read From File \n8.Write Data To CSV File \n9.Read Data From CSV File \n10.Exit Address book System");

			switch (scannerObject.nextInt()) {
			case 1:
				addContact();
				break;
			case 2:
				editContact();
				break;
			case 3:
				displayContents();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				System.out.println("What Criteria Do You Want Address Book To Be Sorted In ?");
				System.out.println("1.FirstName\n2.City\n3.State\n4.Zip Code");
				int sortingChoice = scannerObject.nextInt();
				sortAddressBook(sortingChoice);
				break;
			case 6: 
				writeToAddressBookFile(IOService.FILE_IO);
				break;
			case 7:
				readDataFromFile(IOService.FILE_IO);
				break;
			case 8:
				try {
                    writeDataToCSV();
                }catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    e.printStackTrace();
                }
				break;
			case 9:
				try {
                    readDataFromCSV();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                break;
			case 10:
				moreChanges = false;
				System.out.println("Exiting Address Book: "+this.getAddressBookName()+" !");

			}

		} while (moreChanges);
	}

	/**
	 * We have created this class to take number of contacts from the user. We have
	 * used the For loop and called the check method.
	 */
	public void addContact() {
		System.out.println("Enter the number of contacts you want to enter");
		int number = scannerObject.nextInt();
		for (int i = 0; i < number; i++) {
			System.out.println("Enter the contact details of person ");
			addPerson();
		}
	}

	/**
	 * [1] This method is to take the input from console and set the values of the contact. 
	 * 1. We have just take the input from console and saved it in a variable. 
	 * 2. Then we are using the setters to set the contact values. 
	 * 3. Then we are calling the add method to add the data to ArrayList.
	 */
	public void addPerson() {

		ContactPerson person = new ContactPerson();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter First Name: ");
		String firstName = scan.next();

		if (contact.containsKey(firstName)) {
			System.out.println("Contact Already Exists");
			return;
		}

		System.out.print(" Please enter the last name: ");
		String lastName = scan.next();

		System.out.print(" Please enter the address: ");
		String address = scan.next();

		System.out.print(" Please enter the city: ");
		String city = scan.next();

		System.out.print(" Please enter the state: ");
		String state = scan.next();

		System.out.print(" Please enter the zip: ");
		int zipCode = scan.nextInt();

		System.out.print(" Please enter the phone number: ");
		Long phoneNumber = scan.nextLong();

		System.out.print(" Please enter the email: ");
		String email = scan.next();

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setEmail(email);
		person.setCity(city);
		person.setState(state);
		person.setZip(zipCode);
		addPersonToCity(person);
		addPersonToState(person);
		contact.put(firstName, person);

		contact.put(firstName, person);
	}

	/**
	 * [2] The editContact method will edit the contact in the list.
	 * 1. We are using the containsKey method to check for the first name.
	 * 2. Then we are using the switch to edit a specific variable.
	 * 3. We are using the setters to edit the values.
	 */
	public void editContact() {

		ContactPerson person = new ContactPerson();

		System.out.println("Enter the first name:");
		String firstName = scannerObject.next();

		if (contact.containsKey(firstName)) {
			person = contact.get(firstName);

			System.out.println("\nChoose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = scannerObject.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the correct Last Name :");
				String lastName = scannerObject.next();
				person.setLastName(lastName);
				break;
			case 2:
				System.out.println("Enter the correct Phone Number :");
				long phoneNumber = scannerObject.nextLong();
				person.setPhoneNumber(phoneNumber);
				break;
			case 3:
				System.out.println("Enter the correct Email Address :");
				String email = scannerObject.next();
				person.setEmail(email);
				break;
			case 4:
				System.out.println("Enter the correct City :");
				String city = scannerObject.next();
				person.setCity(city);
				break;
			case 5:
				System.out.println("Enter the correct State :");
				String state = scannerObject.next();
				person.setState(state);
				break;
			case 6:
				System.out.println("Enter the correct ZipCode :");
				long zip = scannerObject.nextLong();
				person.setZip(zip);
				break;
			}
		} else {
			System.out.println("Contact Does Not Exist With Given Name");
		}

	}

	/**
	 * [3] Method delete to delete a specific contact.
	 * 1. We will use the key to find the contact.
	 * 2. Then we will call the remove method to delete the contact from the list.
	 */
	public void deletePerson() {

		System.out.println("Enter the first name of the person to be deleted");
		String firstName = scannerObject.next();
		if (contact.containsKey(firstName)) {
			contact.remove(firstName);
			System.out.println("Successfully Deleted");
		} else {
			System.out.println("Contact Not Found!");
		}

	}
	
	/**
	 *  [4] Method to add the contact person to city list.
	 *  In this we are checking if the key of City Hash map contains the given city
	 *  If not then we will add it
	 *  Else we are adding the contact to arrayList and then adding the contact to HashMap of city.
	 *  
	 * @param contact - we will pass the contact
	 */
	public void addPersonToCity(ContactPerson contact) {
		if (personByCity.containsKey(contact.getCity())) {
			personByCity.get(contact.getCity()).add(contact);
		} else {
			ArrayList<ContactPerson> cityList = new ArrayList<ContactPerson>();
			cityList.add(contact);
			personByCity.put(contact.getCity(), cityList);
		}
	}

	/**
	 *  [5] Method to add the contact person to State list.
	 *  In this we are checking if the key of state Hash map contains the given state
	 *  If not then we will add it
	 *  Else we are adding the contact to arrayList and then adding the contact to HashMap of state.
	 *  
	 * @param contact - we will pass the contact
	 */
	public void addPersonToState(ContactPerson contact) {
		if (personByState.containsKey(contact.getState())) {
			personByState.get(contact.getState()).add(contact);
		} else {
			ArrayList<ContactPerson> stateList = new ArrayList<ContactPerson>();
			stateList.add(contact);
			personByState.put(contact.getState(), stateList);
		}
	}

	/**
	 * [6] Method to sort address Book by fname/state/ city/ zip
	 * We are using the streams to sort the address book.
	 * Sorted method to sort the stream in acceding order.
	 * @param sortingChoice
	 */
	public void sortAddressBook(int sortingChoice) {
		List<ContactPerson> sortedContactList;

		switch (sortingChoice) {

		case 1:
			sortedContactList = contact.values().stream().sorted(
					(firstperson, secondperson) -> firstperson.getFirstName().compareTo(secondperson.getFirstName()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 2:
			sortedContactList = contact.values().stream()
					.sorted((firstperson, secondperson) -> firstperson.getCity().compareTo(secondperson.getCity()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 3:
			sortedContactList = contact.values().stream()
					.sorted((firstperson, secondperson) -> firstperson.getState().compareTo(secondperson.getState()))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;

		case 4:
			sortedContactList = contact.values().stream().sorted((firstperson, secondperson) -> Long
					.valueOf(firstperson.getZip()).compareTo(Long.valueOf(secondperson.getZip())))
					.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;
		}

	}
	
	/**
	 * [7] Method to print the sorted address Book
	 * We are using the Iterator to dsplay the sorted contacts.
	 * @param sortedContactList
	 */
	public void printSortedList(List<ContactPerson> sortedContactList) {
		System.out.println("------ Sorted Address Book " + this.getAddressBookName() + " ------");
		Iterator iterator = sortedContactList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			System.out.println();
		}
		System.out.println("-----------------------------------------");
	}

	/**
	 * Method display to list the contacts.
	 * This method will display the contacts of the list
	 */
	public void displayContents() {

		System.out.println("----- Contents of the Address Book " + this.getAddressBookName() + " -----");
		for (String eachContact : contact.keySet()) {
			ContactPerson person = contact.get(eachContact);
			System.out.println(person);
		}
		System.out.println("-----------------------------------------");

	}

	/**
	 * [8] Method to write the data to file 
	 * 1. We are creating the file with the name of address book name.
	 * 2. Then we are using the String Buffer to append the data.
	 * 3. We are converting the contact to string and then appending it.
	 */
	public void writeToAddressBookFile(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
			displayContents();
		
		else if(ioService.equals(IOService.FILE_IO)) {
			String bookName = this.getAddressBookName();
			String fileName = bookName+".txt";
			new AddressBookFileIO().writeToAddressBookFile(fileName, contact);
		}
	}

	/**
	 * [9] Method to read the data from the file and display in console.
	 * 1. We will check for the file with the address book name.
	 * 2. Then we will read the file and the data in it.
	 * 3. We will then add the contact to the list.
	 * @return
	 */
	public List<String> readDataFromFile(IOService fileIo) {
		
		List<String> employeePayrollFromFile = new ArrayList<String>();
		if(fileIo.equals(IOService.FILE_IO)) {
			System.out.println("Employee Details from payroll-file.txt");
			String bookName = this.getAddressBookName();
			String fileName = bookName+".txt";
			employeePayrollFromFile = new AddressBookFileIO().readDataFromFile(fileName);
			
		}
		return employeePayrollFromFile;
	}
	
	public void printData(IOService fileIo) {
		String bookName = this.getAddressBookName();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) new AddressBookFileIO().printData(fileName);
	}


	public long countEntries(IOService fileIo) {
		
		String bookName = this.getAddressBookName();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) 
			return new AddressBookFileIO().countEntries(fileName);
		
		return 0;
	}
	/**
	 * [10] Method to write the data to a CSV file
	 * @throws IOException -  to throw any exception if occurred.
	 */
	public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

		String fileName = "./" + this.getAddressBookName() + ".csv";
		try (Writer writer = Files.newBufferedWriter(Paths.get(fileName));) {

			StatefulBeanToCsvBuilder<ContactPerson> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<ContactPerson> beanWriter = builder.build();
			ArrayList<ContactPerson> listOfContacts = contact.values().stream()
					.collect(Collectors.toCollection(ArrayList::new));
			beanWriter.write(listOfContacts);
			writer.close();
			System.out.println("Written To CSV Successfully !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * [11] Method to read data from a CSV file
	 * @throws IOException - we will throw the IO exception
	 */
	public <CsvValidationException extends Throwable> void readDataFromCSV() throws IOException, CsvValidationException {

		String fileName = "./" + this.getAddressBookName() + ".csv";
		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {

			String[] nextRecord;
			try {
				while ((nextRecord = csvReader.readNext()) != null) {
					System.out.println("First Name = " + nextRecord[2]);
					System.out.println("Last Name = " + nextRecord[3]);
					System.out.println("City = " + nextRecord[0]);
					System.out.println("State = " + nextRecord[5]);
					System.out.println("Email = " + nextRecord[1]);
					System.out.println("Phone Number = " + nextRecord[4]);
					System.out.println("Zip Code = " + nextRecord[6]);
					System.out.println("\n");
				}
			} catch (com.opencsv.exceptions.CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}