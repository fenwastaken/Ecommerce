package database;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import log.Log;

public class MySQL {

	public static Connection connection = null;
	public static String ip = "127.0.0.1";
	public static String port = "3306";
	public static String user = "root";
	public static String password = "admin";
	public static String database = "ecommerce";

	/**
	 * set connection with default parameters
	 */
	public static void setConnection(){
		
		if(connection == null){
			try {
				connection  = (Connection) DriverManager.getConnection("jdbc:mysql://" 
			+ ip + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useSSL=false");
				Log.debug("Connecté");
				//System.out.println("Connexion succeeded\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//System.out.println("connection failed");
				e.printStackTrace();
				Log.catchError(e);
			} 
		}
	}
	
	/**
	 * Set connection with custom parameters
	 * @param ip
	 * @param port
	 * @param user
	 * @param password
	 */
	public static void setConnection(String ip, String port, String user, String password, String database){
		if(connection == null){
			try {
				connection  = (Connection) DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useSSL=false");
				System.out.println("Connexion succeeded\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} 
		}
	}

	public static Connection getConnection(){
		return connection;
	}

	public static void closeConnection(){
		if(connection != null){
			try {
				connection.close();
				System.out.println("\nConnection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("\nCouldn't close connection");
				e.printStackTrace();
			}
		}
	}

}
