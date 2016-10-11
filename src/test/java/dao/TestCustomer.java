package dao;

import java.util.Date;

import org.junit.Test;

public class TestCustomer {

	@Test
	public boolean testCustomer(){
		String id = "id";
		String firstName = "fname";
		String lastName = "lname";
		String gender = "gender";
		int age = 99;
		String address = "address";
		String email = "email";
		Date date  = new Date(System.currentTimeMillis());
		
		Customer customer = new Customer(id, firstName, lastName, gender, age, address, email, date);
		
		boolean isValid = true;
		
		if(!customer.getId().equals(id)){
			isValid = false;
		}
		
		if(!customer.getFirstName().equals(firstName)){
			isValid = false;
		}
		
		if(!customer.getLastName().equals(lastName)){
			isValid = false;
		}
		
		if(!customer.getGender().equals(gender)){
			isValid = false;
		}
		
		if(!(customer.getAge() == age)){
			isValid = false;
		}
		
		if(!customer.getAddress().equals(address)){
			isValid = false;
		}
		
		if(!customer.getEmail().equals(email)){
			isValid = false;
		}
		
		return isValid;
	}
	
	
}
