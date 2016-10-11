package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Vector;

import database.MySQL;

public class CustomerManager {

	public static int deleteAllCustomers() throws SQLException{
		
		basketManager.deleteAllBaskets();
		
		String sql = "DELETE FROM Customer";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		return st.executeUpdate();
		
	}
	
	public static int deleteCustomerFromName(String name) throws SQLException{
		basketManager.deleteBasketFromID(CustomerManager.getIdFromName(name));
		String sql = "DELETE FROM Customer WHERE LOWER(firstname) = ?";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name.toLowerCase());
		return st.executeUpdate();
		
	}
	
	public static String addCustomer(Customer customer) throws SQLException{
		String sql = "INSERT INTO Customer(id, firstname, lastname, gender, age, address, email, creationdate) VALUES(?, ?, ?, ?, ?, ?, ?, CURDATE())";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		String id = UUID.randomUUID().toString();
		st.setString(1, id);
		st.setString(2, customer.getFirstName());
		st.setString(3, customer.getLastName());
		st.setString(4, customer.getGender());
		st.setInt(5, customer.getAge());
		st.setString(6, customer.getAddress());
		st.setString(7, customer.getEmail());
		st.executeUpdate();
		
		basketManager.addBasket(CustomerManager.getIdFromName(customer.getFirstName()));
		
		return id;
	}
	
	public static String getIdFromName(String name) throws SQLException{
		String sql = "SELECT id FROM Customer WHERE LOWER(firstName) = ?";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name.toLowerCase());
		ResultSet rs = st.executeQuery();
		String id = null;
		if(rs.next()){
			id = rs.getString("id");
		}
		return id;
	}
	
	public static Vector<Customer> getAllCustomers() throws SQLException{
		String sql = "SELECT * FROM Customer ORDER BY firstname";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		Customer customer = null;
		
		Vector<Customer> vecCustomer = new Vector<Customer>();
		while(rs.next()){
			String id = rs.getString("id");
			String firstName = rs.getString("firstname");
			String lastName = rs.getString("lastname");
			String gender = rs.getString("gender");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			String email = rs.getString("email");
			Date date = rs.getDate("creationdate");
			customer = new Customer(id, firstName, lastName, gender, age, address, email, date);
			vecCustomer.add(customer);
		}
		return vecCustomer;
	}
	
	public static Customer getCustomerFromName(String name) throws SQLException{
		String sql = "SELECT id, firstname, lastname, gender, age, address, email, creationdate FROM Customer WHERE firstname = ?";
		PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		
		Customer customer = null;
		
		if(rs.next()){
			String id = rs.getString("id");
			String firstName = rs.getString("firstname");
			String lastName = rs.getString("lastname");
			String gender = rs.getString("gender");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			String email = rs.getString("email");
			Date date = rs.getDate("creationdate");
			customer = new Customer(id, firstName, lastName, gender, age, address, email, date);
		}
		return customer;
	}
	
	public static void displayAllCustomers() throws SQLException{
		toString(getAllCustomers());
	}
	
	public static void toString(Vector<Customer> vec){
		int i = 0;
		for(Customer cstmr : vec){
			i++;
			System.out.println("Customer " + i + " : " + cstmr.toString());
		}
	}
	
}
