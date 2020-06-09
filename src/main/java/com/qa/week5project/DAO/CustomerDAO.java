package com.qa.week5project.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qa.week5project.Ims;
import com.qa.week5project.Models.Customer;
import com.qa.week5project.Utils.Input;


//Database Connection class for Customer Table - interacts with Customer Table in Database
//This class is an example of open for extension closed for modification
public class CustomerDao  {
	
	private DatabaseConnection databaseConnection;
	private ResultSet rs = null;
	Input input = new Input();
	private int id = 0;
	private String name = null;
	
	public List<String> list=new ArrayList<>();
	
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
	
	public ResultSet selectCustomers() throws SQLException {
		String sql = "select * from customers";
		
		this.rs = databaseConnection.sendQuery(sql);
		//System.out.println(import buffer));
		while(this.rs.next()){
	         //Retrieve by column name
	        
			//rs.getString ask for the columname
	         String name = this.rs.getString("customer_name");
	         String fav_colour = this.rs.getString("customer_fav_colour");
	         int id = rs.getInt(1);
	         //Display values
	         System.out.println(id);
	         System.out.println( "Name: " + name);
	         System.out.print("Favorite Color: " + fav_colour);
	       
	     	
	      }
		return rs;	
		
		
	}
	
	public ResultSet showRow(int ID) {
		System.out.println("This is located at Row" + ID);
		String sql = "select customer_name, customer_fav_colour from customers where customer_id = "+ID+";";
		
		this.rs = databaseConnection.sendQuery(sql);
		
		return rs;
		
	}
	
	public ResultSet ShowRow(String name) {
		
		return rs;
		
	}
	
	public void editCustomer(int ID, String newName) throws SQLException {
		//update customers set customer_name = "David" where id = 8;
		
		String sql = "update customers set customer_name = '" +newName+ "' where customer_id = "+ID+";";
		databaseConnection.sendUpdate(sql);
		 
		
		
		//String name = "";
		//String last = +"";
		

	     
	            //list.add(rs.getString(1));
	            //System.out.println(list.get(0));
	         //how do i close rs.close();
	      }

	public void deleteCustomer(int cID) {
		// TODO Auto-generated method stub
		String sql = "delete from customers where customer_id = "+cID+";";
		databaseConnection.sendUpdate(sql);
		
		System.out.println("Delete succesful");
		String newRoundMessage = "Type a menu name to continue or 'Exit' to leave";
		Ims ims = new Ims();
		
		ims.start(newRoundMessage, false);
	}	
	
		
		


}