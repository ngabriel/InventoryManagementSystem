package com.qa.week5project.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.qa.week5project.Models.Customer;


//Database Connection class for Customer Table - interacts with Customer Table in Database
//This class is an example of open for extension closed for modification
public class CustomerDao  {
	
	private DatabaseConnection databaseConnection;
	
	//Constructor class needed as is extended form another Class
	//the constructor class we take in any connection passed into it
	public CustomerDao(DatabaseConnection databaseConnection) {
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
	
	public void viewCustomers() throws SQLException {
		String sql = "select * from customers";
		ResultSet rs = databaseConnection.sendQuery(sql);
		//System.out.println(import buffer));
		while(rs.next()){
	         //Retrieve by column name
	        
	         String name = rs.getString("customer_name");
	         String last = rs.getString("customer_fav_colour");
	         int id = rs.getInt(1);
	         //Display values
	      
	         System.out.println("Customer ID: "+id + " Name: " + name);
	         //System.out.print("Name: " +name);
	         //System.out.println(last);
	        
	         //how do i close rs.close();
	      }
		

		
		
	}

}
