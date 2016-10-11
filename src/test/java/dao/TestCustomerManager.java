package dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCustomerManager {

	@Test
	public void addCustomer(){
		
		Customer customer = new Customer("lol", "tata", "titi", "male", 20, "adresse", "email", new Date(System.currentTimeMillis()));
		try {
			String id = CustomerManager.addCustomer(customer);
			Assert.assertEquals("lol", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	
	
}
