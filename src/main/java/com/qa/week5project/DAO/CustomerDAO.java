package com.qa.week5project.DAO;

import java.sql.SQLException;

import com.qa.week5project.Models.Customer;


//Database Connection class for Customer Table - interacts with Customer Table in Database
//This class is an example of open for extension closed for modification
public class CustomerDAO  {
	
	private DatabaseConnection databaseConnection;
	
	//Constructor class needed as is extended form another Class
	//the constructor class we take in any connection passed into it
	public CustomerDAO(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		// TODO Auto-generated constructor stub
	}

	//method to create sql string pass into the sendUpdate method inside parent class
	public void insertCustomer(Customer customer){

	//create the sql code
		
		 
		//String query = "INSERT INTO customers(customer_name) VALUES ( '" +customer_name +"')";
		 
		String sql = "insert into customers(customer_name, customer_fav_colour) values('"
				+ customer.getName() + "', '"
				+ customer.getFavColour() + "');";
		System.out.println(sql);
		
		//CustomerDa Extends DatabaseConnection so we can use DatabaseConnection methods
		//Use Send update method
		
			databaseConnection.sendUpdate(sql);
		
				
	}

}
