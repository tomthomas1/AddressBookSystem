package com.addressBook.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
	List<ContactPerson> contacts = new ArrayList<ContactPerson>();
	Map<String, AddressBookServices> addressBookMap = new HashMap<>(); 
	ContactPerson person = new ContactPerson();

	/**
	 * In this method we are checking if the contacts added is duplicate or not with reference to the first name.
	 * 1. We are taking first name from the user.
	 * 2. Then we are checking in the ArrayList if the name matches the present firstname.
	 * 3. If present then it will give error.
	 * 4. Else it will call the addPerson Method
	 */
	public void duplicateCheck() {  
		System.out.print(" Please enter the first name: ");
		name = sc.next();
		for(ContactPerson i : contacts) {
		   if(i.getFirstName().equals(name)) {
			   System.out.println(" Given name already exists");
		   } return;
        }  addPerson();
	}
	
	/**
	 * We have created this class to take number of contacts from the user.
	 * We have used the For loop and called the check method.
	 */
	public void addContact() {                                                            
		System.out.println("Enter the number of contacts you want to enter");
        int number = sc.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Enter the contact details of person ");
            duplicateCheck();
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
		String firstName = name;

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
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setEmail(email);
		person.setCity(city);
		person.setState(state);
		person.setZip(zip);
		contacts.add(person);
		
	}

    /**
     *  We have created the findContact method to find a specific contact in ArrayList for manipulation.
     *  1. We are taking the firstname from the console. 
     *  2. Then will advanced for loop we are iterating through the ArrayList 
     *  3. If the name matches then we will increment the duplicate counter.
     *  4. We have created the duplicate counter to check if same name exists twice.
     *  5. Else it will return the contact.
     * @return - It will return the contact to take action on.
     */
	public ContactPerson findContact() {                                         
		System.out.println("\n Enter the first name of the contact to edit: ");
		String name = sc.next();
		int duplicate = 0;                                                   
		ContactPerson temp = null;
		for (ContactPerson contact : contacts) {
			if (contact.getFirstName().equals(name)) {
				duplicate++;
				temp = contact;
			}
		}
		if (duplicate == 1) {
			return temp;

		} else if( duplicate > 1) {
			System.out.print(" There are multiple contacts with given name.\n Please enter their email id: ");
			String email = sc.next();
			for (ContactPerson contact : contacts) {
				if (contact.getFirstName().equals(name) && contact.getEmail().equals(email)) {
					return contact;
				}
			}
		}
		else{
			System.out.println("No contact with the given first name. Please enter the correct first name");
			findContact();
		}
		return temp;
	} 

	/**
	 * [2] The editContact method will edit the contact in the list.
	 * 1. We are using the find contact method to get the contact from the list.
	 * 2. Then we are using the switch to exit a specific variable.
	 * 3. We are using the setters to edit the values.
	 */
	public void editContact() {

		ContactPerson contact = findContact();

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
	
	/**
	 * [3] Method display to list the contacts.
	 * This method will display the contacts of the list
	 */
	public void displayContact() {                                                       
			System.out.println(contacts);
	}
	
	/**
	 * [4] Method delete to delete a specific contact.
	 * 1. We will call the find method to get the contact.
	 * 2. Then we will call the remove method to delete the contact from the list.
	 */
	public void deleteContact() {                                                                      
		ContactPerson contact = findContact();
		if (contact == null) {
			System.out.println("No contact found with the given name");
			return;
		}
		contacts.remove(contact);                                                                        
		System.out.println("The contact has been deleted from the Address Book");
	}

}