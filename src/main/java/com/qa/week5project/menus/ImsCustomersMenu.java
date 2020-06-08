package com.qa.week5project.menus;

import com.qa.week5project.dao.CustomerDao;
import com.qa.week5project.dao.LocalDatabaseConnection;

import java.sql.SQLException;

import com.qa.connecting.dao.RemoteDatabaseConnection;
import com.qa.week5project.Models.Customer;
import com.qa.week5project.Utils.Action;
import com.qa.week5project.Utils.Input;
import com.qa.week5project.Utils.Menus;

public class ImsCustomersMenu {
	Input input = new Input();
	Action selectedAction;

	public void start() {
		
		System.out.println("Welcome to the Customers menu, what next?");
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
				System.out.println("Computers says no. Please re-enter");
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

		
		connection.closeConnection();
		
	}
	private void viewCustomers() {
		LocalDatabaseConnection connection = new LocalDatabaseConnection("root", "root");
		CustomerDao cD = new CustomerDao(connection);
		try {
			cD.viewCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
		
	}
	private void editCustomer() {
		// TODO Auto-generated method stub
		
	}
	
	private void deleteCustomer() {
		// TODO Auto-generated method stub
		
	}
}
