package com.qa.week5project.DAO;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.week5project.exceptions.ConnectionNotMadeException;
import com.qa.week5project.exceptions.SQLStatementNotUnderstoodException;



//This is the superclass
public abstract class DatabaseConnection implements Openable, Closeable, Queryable {

	//create variable needed for creating a connection
	private Connection connection;
	private String username;
	private String password;
	
	//creat database connection here to be used in both LocalDatabase and RemoteDatabase
	public DatabaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
		openConnection();
	}
	

	//classes that use implements must use the implements' methods
	// this openConnection() method will be overridden by openConnection method in RemoteDataba.. and Locald
	
	public abstract void openConnection();

	//Instead of creating getters and setters for the connection, we can create methods to interact wiht connection
	public void closeConnection() {
	
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ConnectionNotMadeException("Could not close connection");
			}
	
	}
	
	//Create a query method, Result set from database is returned and pass in string/sql statement to database

	
	//we want to create an exception here (throws SQLException) but our sendQuery method should match the method in our Queryable interface so we cant put it in the method signature
	public ResultSet sendQuery(String sql) {
		try {
		//To request a query we create a instance of statement object  from the createStatement method inside the connection class.
		
		
			Statement statement = connection.createStatement();
			
			ResultSet resultset = statement.executeQuery(sql);
			 
			statement.close();
			return resultset;
		} 
			catch (SQLException e) {
			//e.printStackTrace();
			//we still need an exception but if we use run time exceptions they do not need to be a part of the method signature
			throw new SQLStatementNotUnderstoodException("Could not query with " + sql);
		}
	}
		
		
		//if we just threw an generated exception and returned null we dont get any information back
		
	
	
		//create the result set using the statement instance, we call the executeQuery method from statement clases and push in our sql String 
	
	
	//Create and update method, nothing is returned but we send a string to the database
	public void sendUpdate(String sql) 
	{
		try {
			//Create a statement instance through Connection Class
			Statement statement = connection.createStatement();
			//use statement instances method executeUpdate to pass sql String to database to update with statement
			statement.executeUpdate(sql);
			statement.close();
			
			
		} catch (SQLException e){
			throw new SQLStatementNotUnderstoodException("Could not query with" + sql);
		}
		
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
