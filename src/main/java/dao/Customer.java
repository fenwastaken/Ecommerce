package dao;

import java.util.Date;

public class Customer {

	public String id;
	public String firstName;
	public String lastName;
	public String gender;
	public int age;
	public String address;
	public String email;
	public Date creationDate;
	
	public Customer(String id, String firstName, String lastName,
			String gender, int age, String address, String email, Date date) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.email = email;
		this.creationDate = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String toString(){
		return this.firstName + " " + this.lastName + ", " + this.gender + ", " + this.age + "yo, " + this.address + ", " + this.email + ", " + this.creationDate.toString();
		
	}
	
}
