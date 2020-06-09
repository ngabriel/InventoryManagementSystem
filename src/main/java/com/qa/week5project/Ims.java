package com.qa.week5project;

import com.qa.week5project.Utils.Input;

import com.qa.week5project.Utils.Menus;
import com.qa.week5project.dao.CustomerDao;
import com.qa.week5project.dao.DatabaseConnection;
import com.qa.week5project.dao.LocalDatabaseConnection;
import com.qa.week5project.menus.ImsCustomersMenu;
import com.qa.week5project.menus.ImsItemsMenu;
import com.qa.week5project.menus.ImsOrderMenu;

import java.awt.Desktop.Action;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.qa.week5project.Models.Customer;

public class Ims {


	//declare a logger and pass in the class, every logger needs class
 	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	// continue watching video from wednesday 3rd june recorded by kart end of day
	// fofrom 10.43 for rest of logger example

	Menus selectedMenu;
	Input input = new Input();

	private String message;

	public void start() {
		
		//LOGGER.trace("");
		//LOGGER.debug("");
		//LOGGER.info("");
		//LOGGER.warn("");
		//LOGGER.error("");
		//LOGGER.fatal("");

		//System.out.println("Database username: ");
		//String user = input.getString();
		//System.out.println("Database password: ");
		//String password = input.getString();

		// here we assign which database we want to connect to, LocalDatabase....
		// RemoteDatab.... FakeConnec....
		LocalDatabaseConnection localConnection = new LocalDatabaseConnection("root","root");
		

		System.out.println(message);
		// iterate through our ations enum\s

		for (Menus menu : Menus.values()) {
			System.out.println(menu.name());
		}

		System.out.println("------");
		while (true) {
			try {
				
				String menuInput = input.getString();
				selectedMenu = Menus.valueOf(menuInput.toUpperCase());
				break;
			} catch (NullPointerException | IllegalArgumentException e) {
				// Logger.debug(e.getStackTrace());
				// Logge.r.info(("Computer says no. Please re-enter"))
				LOGGER.warn("Not a valid choice, try again");
			}
		}
		
		
		System.out.println(selectedMenu);

		switch (selectedMenu) {
		case CUSTOMER:
			//System.out.println("Going customer menu");
			ImsCustomersMenu imsCM = new ImsCustomersMenu();
			imsCM.start("Welcome to Customer Menu");
			System.out.println("after customer menu");
			break;
		case ITEM:
			System.out.println("Going to item menu");
			ImsItemsMenu imsIM = new ImsItemsMenu();
			imsIM.start();
			System.out.println("after items menu");
			break;
		case ORDER:
			System.out.println("Going to Order menu");
			ImsOrderMenu imsOM = new ImsOrderMenu();
			imsOM.start();
			System.out.println("after  Order menu");
			break;

		}
		// DONT FORGET TO CLOSE OFF CONNECTIONS
				localConnection.closeConnection();

	}

	
	// System.out.println("customer address:");
	// String address = input.getInput();

}
