package com.qa.week5project.dao;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.week5project.Models.Customer;

import org.junit.After;
import org.junit.AfterClass;



public class CustomerDAOTest {
	
	static DatabaseConnection databaseConnection;
	
	//Strings to let locate test database src code
	static final String SCHEMA_LOCATION= "src/test/resources/IVMSchema.sql";
	static final String DATA_LOCATION= "src/test/resources/InputsCustomers.sql";
	static final String CLEAR_LOCATION= "src/test/resources/ClearDB.sql";
	static final String DROP_LOCATION= "src/test/resources/DropDBTestivm.sql";
	
	///create a method to send to DB so we can re use this.
	public static void sendToDB(Connection connection, String fileLocation) {
		try (
				//use a buffered reader to read from file, reads each file line by line to restructure our test database
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));)
				{
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement()){
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	//call into database and create our new database
	public static void init() throws SQLException {
		//Set up connection to server and database root location, by connecting to the root we can easily drop the database later - becauase we cant drop a database whilse inside a database
		sendToDB(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "root"), SCHEMA_LOCATION);
	}
	
	@Before 
	//create a new database connection - used for testing 
	public void setup() {
		databaseConnection = new TestingDatabaseConnection("root" , "root");
		//.getConnection returns the name of our database
		sendToDB(databaseConnection.getConnection(), DATA_LOCATION);
	
	}
	
	
	@After
	public void teardown() throws SQLException {
		//databaseConnection.getConnection is the same asn above DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "root"
		sendToDB(databaseConnection.getConnection(),  CLEAR_LOCATION);
	}
	
	@AfterClass
	public static void finish() throws SQLException {
		sendToDB(databaseConnection.getConnection(),  DROP_LOCATION);
		databaseConnection.closeConnection();
	}
	
	
	@Test
	public void test() throws SQLException {
		//We can test our insert customer method
		 
		CustomerDao customerdao = new CustomerDao(databaseConnection);
		Customer test = new Customer("baNaomi","Blue");
		
		customerdao.insertCustomer(test);

		//create this query which will count the cusomers in our database
		String query = "SELECT * FROM customers";
		//send this query and get the results back 
		ResultSet rs = databaseConnection.sendQuery(query);
		
		int count = 0;
		//iterate though each result set and count each rs
		while (rs.next()) {
			count++;
			
		}
		System.out.println(count);
		assertEquals(7, count);
		
		
	}
	
}
