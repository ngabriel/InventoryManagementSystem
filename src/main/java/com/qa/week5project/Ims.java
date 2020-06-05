package com.qa.week5project;

import com.qa.week5project.Utils.Input;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.qa.week5project.DAO.CustomerDAO;
import com.qa.week5project.DAO.DatabaseConnection;
import com.qa.week5project.DAO.LocalDatabaseConnection;
import com.qa.week5project.Models.Customer;


public class Ims {
	
	//each logger will need to be in each class and you pass the calss name in the getLogger parameter.
//	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	//continue watching video from wednesday 3rd june recorded by kart end of day fofrom 10.43 for rest of logger example

	Input input = new Input();

	public void start(){

		System.out.println("Database username: ");
		String user = input.getInput();
		System.out.println("Database password: ");
		String password = input.getInput();
		

		//here we assign which database we want to connect to, LocalDatabase.... RemoteDatab.... FakeConnec....
		LocalDatabaseConnection localConnection = new LocalDatabaseConnection(user, password);

		
		// Below needs to be similar to Garage project
		// where different type of objects can be built and different inputs are required
		// --------------------------
		System.out.println("customer name:");
		String name = input.getInput();

		System.out.println("What is your faveourite colour?");
		String favColour = input.getInput();

		//System.out.println("customer address:");
		//String address = input.getInput();

		Customer customer = new Customer(name, favColour);
		// ----------------------------
		
		// With the object send it to the Dao and have it do the rest
		CustomerDAO customerDao = new CustomerDAO(localConnection);
		//try {
			customerDao.insertCustomer(customer);
		//} catch (SQLException e) {
			//if customer detatils are incorrect, we could change the cutomer and try and resend it 
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		// DONT FORGET TO CLOSE OFF CONNECTIONS
		localConnection.closeConnection();
	}
}
