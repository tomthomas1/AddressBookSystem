package com.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.addressBook.entity.ContactPerson;

public class Statements {

	final String READ_QUERY = "SELECT * from contact ";

	public ArrayList<ContactPerson> readAddressBook() {

		Connection connection = Config.connection;
		ArrayList<ContactPerson> addressBookDB = new ArrayList<>();

		try {
			Statement fetchStatement = connection.createStatement();
			ResultSet result = fetchStatement.executeQuery(READ_QUERY);

			while (result.next()) {
				addressBookDB
						.add(new ContactPerson(result.getString(1), result.getString(2),
								result.getString(3), result.getString(4),result.getString(5),result.getInt(6), result.getInt(7),result.getString(8)));
			}
			
			System.out.println(" The stuff in the db: ");
			System.out.println(addressBookDB);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addressBookDB;
	}
}
