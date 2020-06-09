package com.qa.week5project.menus;

import com.qa.week5project.dao.CustomerDao;
import com.qa.week5project.dao.LocalDatabaseConnection;
import com.qa.week5project.inventories.CustomerInventory;

import java.sql.ResultSet;
import java.sql.SQLException;

//import com.qa.week5project.RemoteDatabaseConnection;
//import com.qa.week5project.Switch;
import com.qa.week5project.Ims;
import com.qa.week5project.Models.Customer;
import com.qa.week5project.Utils.Action;
import com.qa.week5project.Utils.Input;
import com.qa.week5project.Utils.Menus;

public class ImsCustomersMenu {
	Input input = new Input();
	
	Action selectedAction;
	String newInput;
	
	ResultSet results = null;
	private Object idOrName;
	CustomerDao cD;
	LocalDatabaseConnection connection;

	public void start(String message) {
		
		System.out.println(message);
		loadData();
		
		for (Action action : Action.values()) {
			System.out.println(action.name());
		}

		System.out.println("------");
		while (true) {
			try {
				String actionInput = input.getString();
				selectedAction = Action.valueOf(actionInput.toUpperCase());
			} catch (IllegalArgumentException e) {
				// Logger.debug(e.getStackTrace());
				// Logger.info(("Computer says no. Please re-enter"))
				System.out.println("Computers says no.  - customer");
				this.start("Please re-enter");
			}
			System.out.println(selectedAction + " a customer");
			switch (selectedAction) 
			{
			case ADD:
				System.out.println("Add");
				addCustomer();
				break;
			case VIEW:
				System.out.println("View");
				viewCustomers();
				start("where next");
				break;
			case EDIT:
				System.out.println("Edit");
				editCustomer();
				break;
			case DELETE:
				System.out.println("Delete");
				deleteCustomer();

			}

		
	}

}

	private void loadData() {
		LocalDatabaseConnection connection = new LocalDatabaseConnection("root", "root");
		cD = new CustomerDao(connection);
		try {
			results = cD.selectCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//connection.closeConnection();
		
	}
	
	private void addCustomer() {
		System.out.println("Enter Customer name");
		String name = input.getString();

		System.out.println("and favorite colour");
		String favColour = input.getString();

		Customer customer = new Customer(name, favColour);
		// ----------------------------
		//LocalDatabaseConnection localConnection = new LocalDatabaseConnection(user, password);

		// With the object send it to the Dao and have it do the rest
		LocalDatabaseConnection connection = new LocalDatabaseConnection("root", "root");
		CustomerDao customerDao = new CustomerDao(connection);
		//LocalDatabaseConnection localConnection//
		customerDao.insertCustomer(customer);
		
		//change to logger
		System.out.println("Succesfully added " + name);

		
		connection.closeConnection();
		System.out.println("Type a menu name to continue or exit to leave");
		//Input switchInput = new Input();
		//Switch switch = new Switch();
		//switch.start(switchInput);
		
	}
	private void viewCustomers() {
		connection = new LocalDatabaseConnection("root", "root");
		cD = new CustomerDao(connection);
		try {
			results = cD.selectCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();

		
		
	}
	
	
	private void editCustomer() {
		connection = new LocalDatabaseConnection("root", "root");
		cD = new CustomerDao(connection);
		//int row;
		
		System.out.println("Enter ID of customer you would like to edit");
		Input custID = new Input();
		
		int cID = custID.getInt();
		
		
		
		System.out.println("What name would you like to replace with");
		Input newName = new Input();
		String nName = newName.getString();
		
		
		try {
			cD.editCustomer(cID, nName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(results);
		
		
		
		
		
		
	}
	
	private void deleteCustomer() {
		connection = new LocalDatabaseConnection("root", "root");
		cD = new CustomerDao(connection);
		//int row;
		
		System.out.println("Enter ID of customer you would like to delete");
		Input custID = new Input();
		
		int cID = custID.getInt();
		
		cD.deleteCustomer(cID);
		
		

				
			

			}
		
		
		
	}
