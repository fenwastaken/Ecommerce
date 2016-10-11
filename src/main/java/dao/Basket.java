package dao;

import java.sql.Date;

public class Basket {

	int idCustomer;
	Date dateCreation;
	
	public Basket(int idCustomer, Date dateCreation) {
		super();
		this.idCustomer = idCustomer;
		this.dateCreation = dateCreation;
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
}
