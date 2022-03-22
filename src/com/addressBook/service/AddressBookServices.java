package com.addressBook.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.addressBook.entity.ContactPerson;

/**
 * In this class we are creating methods for the contact manipulation.
 * [1] We have created the addContact method to add the contacts to the list.
 * [2] The editContact method will edit the contact in the list.
 * [3] Method display to list the contacts.
 * [4] Method delete to delete a specific contact.
 * @author Tom
 *
 */
public class AddressBookServices {
	Scanner sc = new Scanner(System.in);
	String name;
	
	/**
	 * We have created a list of ContactPerson type and also a HashMap for multiple address book.
	 */
	public Map<String, ContactPerson> contacts = new HashMap<String,ContactPerson>();
	public static HashMap<String, ArrayList<ContactPerson>> personByCity  = new HashMap<String, ArrayList<ContactPerson>>();
	public static HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String, ArrayList<ContactPerson>>();
		
	/**
	 * We have created this class to take number of contacts from the user.
	 * We have used the For loop and called the check method.
	 */
	public void addContact() {                                                            
		System.out.println("Enter the number of contacts you want to enter");
        int number = sc.nextInt();
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
		
		if(contacts.containsKey(firstName)) {
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
		int zip = scan.nextInt();
		
		System.out.print(" Please enter the phone number: ");
		Long phoneNumber = scan.nextLong();
		
		System.out.print(" Please enter the email: ");
		String email = scan.next();
		
		person.setFirstName(firstName);
		person.setAddress(address);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setEmail(email);
		person.setCity(city);
		person.setState(state);
		person.setZip(zip);
		addPersonToCity(person);
		addPersonToState(person);
   
		contacts.put(firstName, person);
		
	}

	/**
	 * [2] The editContact method will edit the contact in the list.
	 * 1. We are using the find contact method to get the contact from the list.
	 * 2. Then we are using the switch to exit a specific variable.
	 * 3. We are using the setters to edit the values.
	 */
	public void editContact() {

		ContactPerson contact = new ContactPerson();

		System.out.println("Enter the first name:");
		String firstName = sc.next();
		
		if(contacts.containsKey(firstName)) {
			contact = contacts.get(firstName);

		System.out.println("Enter your choice to edit: " + "\n 1.Edit first name" + "\n 2.Edit last name"
				+ "\n 3.Edit address" + "\n 4.Edit city" + "\n 5.Edit state" + "\n 6.Edit zipcode"
				+ "\n 7.Edit phone number"  + "\n 8.Edit email");

		int choice = sc.nextInt();                                 //with the help of setters setting the new details
		switch (choice) {
		case 1:
			System.out.println("Enter new first name");
			String newFirstName = sc.next();
			contact.setFirstName(newFirstName);
			System.out.println("new first name updated");

			break;
		case 2:
			System.out.println("Enter new last name");
			String newLastName = sc.next();
			contact.setLastName(newLastName);
			System.out.println("new last name updated");

			break;
		case 3:
			System.out.println("Enter new address");
			String newAddress = sc.next();
			contact.setAddress(newAddress);
			System.out.println("new newaddress updated");

			break;
		case 4:
			System.out.println("Enter new city");
			String newCity = sc.next();
			contact.setCity(newCity);
			System.out.println("new city updated");

			break;
		case 5:
			System.out.println("Enter new state");
			String newState = sc.next();
			contact.setState(newState);
			System.out.println("new state updated");

			break;
		case 6:
			System.out.println("Enter new zip code");
			int newZipCode = sc.nextInt();
			contact.setZip(newZipCode);
			System.out.println("new zip code updated");
			break;
			
		case 7:
			System.out.println("Enter new phone number");
			long newPhoneNumber = sc.nextLong();
			contact.setPhoneNumber(newPhoneNumber);
			System.out.println("new phone number updated");

			break;
			
		case 8:
			System.out.println("Enter new email");
			String newEmail = sc.next();
			contact.setEmail(newEmail);
			System.out.println("new email updated");

			break;

		default:
			System.out.println("Please enter a number between 1 to 8 only...");
			break;
		}
		System.out.println("The contact after editing is : " + contact);
		}
	}
	
	/**
	 * [3] Method display to list the contacts.
	 * This method will display the contacts of the list
	 */
	public void displayContact() {                                                       
			System.out.println(contacts);
			System.out.println("Value is " + contacts.values());
	}
	
	/**
	 * [4] Method delete to delete a specific contact.
	 * 1. We will call the find method to get the contact.
	 * 2. Then we will call the remove method to delete the contact from the list.
	 */
	public void deleteContact() {                                                                      
		System.out.println("Enter the first name of the person to be deleted");
		String firstName = sc.next();
		if(contacts.containsKey(firstName)) {
			contacts.remove(firstName);
			System.out.println("Successfully Deleted");
		}
		else {
			System.out.println("Contact Not Found!");
		}
		
	}
	
	/**
	 *  In this method we are checking the persob by city
	 * @param contact- We are pasing the contact there
	 */
	public void addPersonToCity(ContactPerson contact) {
		if (personByCity.containsKey(contact.getCity())) {
			personByCity.get(contact.getCity()).add(contact);
		}
		else {
			ArrayList<ContactPerson> cityList = new ArrayList<ContactPerson>();
			cityList.add(contact);
			personByCity.put(contact.getCity(), cityList);
		}
	}


	/**
	 *  In this method we are checking the person by state
	 * @param contact- We are parsing the contact there
	 */
	public void addPersonToState(ContactPerson contact) {
		if (personByState.containsKey(contact.getState())) {			
			personByState.get(contact.getState()).add(contact);
		}
		else {
			ArrayList<ContactPerson> stateList = new ArrayList<ContactPerson>();
			stateList.add(contact);
			personByState.put(contact.getState(), stateList);
		}
	}	
	
	/**
	 * Method to write to the file
	 */
	 public void writeToAddressBookFile() {
			
			
			String fileName = "write.txt";
			
			StringBuffer addressBookBuffer = new StringBuffer();
			contacts.values().stream().forEach(contact -> {
				String personDataString = contact.toString().concat("\n");
				addressBookBuffer.append(personDataString);
			});

			try {
				Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
			} 
			catch (IOException e) {
				e.printStackTrace();
			}

		}
	
	 /**
	  * Method to read from the file
	  * @return - the list
	  */
	 public List<String> readDataFromFile() {
			
			List<String> addressBookList = new ArrayList<String>();
			String fileName = "write.txt";
			System.out.println("Reading from : "+fileName+"\n");
			try {
				Files.lines(new File(fileName).toPath())
					.map(line -> line.trim())
					.forEach(contacts -> {
						addressBookList.add(contacts);
				});
				
			}
			catch(IOException e){
				e.printStackTrace();
			}
			return addressBookList;
		}

}