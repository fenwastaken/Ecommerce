package dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import database.MySQL;

public class TestCustomerManager {

	String testName = "tata";
	
	@Before
	public void init(){
		MySQL.setConnection();
	}
	
	@Test
	public void addCustomer(){
		
		Customer customer = new Customer(null, testName, "titi", "male", 20, "adresse", "email", new Date(System.currentTimeMillis()));
		try {
			String id = null;
			id = CustomerManager.addCustomer(customer);
			Assert.assertNotNull("id non null après insert de customer", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue("id null après insertion de customer", false);
		}
		
	}

	@After
	public void delete(){
		try {
			CustomerManager.deleteCustomerFromName(testName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
