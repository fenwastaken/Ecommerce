package dao;

public class instanceOfItem {

	public String customerID;
	public String itemID;
	public int quantity;
	
	public instanceOfItem(String customerID, String itemID, int quantity) {
		super();
		this.customerID = customerID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
