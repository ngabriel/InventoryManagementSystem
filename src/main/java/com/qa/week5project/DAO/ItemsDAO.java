package com.qa.week5project.dao;

import com.qa.week5project.Models.Item;

public class ItemsDao {

private DatabaseConnection databaseConnection;
	
	//Constructor class needed as is extended form another Class
	//the constructor class we take in any connection passed into it
	public ItemsDao(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		// TODO Auto-generated constructor stub
	}

	//method to create sql string pass into the sendUpdate method inside parent class
	public void insertItem(Item item){

	//create the sql code
		
		 
		//String query = "INSERT INTO customers(customer_name) VALUES ( '" +customer_name +"')";
		 
		String sql = "insert into items(item_name, item_price) values('"
				+ item.getName() + "', '"
				+ item.getPrice() + "');";
		System.out.println(sql);
		
		//CustomerDa Extends DatabaseConnection so we can use DatabaseConnection methods
		//Use Send update method
		
			databaseConnection.sendUpdate(sql);
		
				
	}

}
