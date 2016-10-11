package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Vector;

import database.MySQL;

public class instanceOfItemManager {

	public static int deleteItemFromNames(String itemName, String customerName) throws SQLException{
		String sql = "DELETE FROM basketitems WHERE idcustomer = ? AND iditem = ?";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		String idCustomer =  CustomerManager.getIdFromName(customerName);
		String idItem = ItemManager.getIdFromName(itemName);
		st.setString(1, idCustomer);
		st.setString(2, idItem);
		return st.executeUpdate();
		
	}
	
	public static int addItem(String customerID, String ItemID, int quantity) throws SQLException{
		String sql = "INSERT INTO basketitems(id, idcustomer, iditem, quantity) VALUES (?, ?, ?, ?)";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, UUID.randomUUID().toString());
		st.setString(2, customerID);
		st.setString(3, ItemID);
		st.setInt(4, quantity);
		return st.executeUpdate();
	}
	
	public static void addItemFromNames(String customerName, String itemName, int quantity) throws SQLException{
		String customerID = CustomerManager.getIdFromName(customerName);
		String itemID = ItemManager.getIdFromName(itemName);
		addItem(customerID, itemID, quantity);
	}
	
	public static Vector<String[]> getAllItemsFromName(String name) throws SQLException{
		String sql = "SELECT i.name, b.quantity  FROM ecommerce.item as i "
				+ "JOIN ecommerce.basketitems as b ON b.iditem = i.id "
				+ "JOIN ecommerce.customer as c ON b.idcustomer = c.id "
				+ "WHERE c.firstname = ?;";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		System.out.println("\nCustomer " + name + ": ");
		int price = 0;
		Vector<String[]> vecRet = new Vector<String[]>();
		while(rs.next()){
			String item = rs.getString("i.name");
			int quantity = rs.getInt("b.quantity");
			price = ItemManager.getPriceFromName(item);	
			String[] tabRet = {item, quantity + "", price + "", quantity * price + ""};
			vecRet.add(tabRet);
		}
		return vecRet;
	}
	
	public static void displayAllItemsFromCustomerName(String name) throws SQLException{
		String sql = "SELECT i.name, b.quantity  FROM ecommerce.item as i "
				+ "JOIN ecommerce.basketitems as b ON b.iditem = i.id "
				+ "JOIN ecommerce.customer as c ON b.idcustomer = c.id "
				+ "WHERE c.firstname = ?;";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		System.out.println("\nCustomer " + name + ": ");
		int i = 0;
		int total = 0;
		int price = 0;
		while(rs.next()){
			i++;
			String item = rs.getString("i.name");
			int quantity = rs.getInt("b.quantity");
			price = ItemManager.getPriceFromName(item);
			System.out.println("  Item " + i + ": " + item + "*" + quantity + " (" + price + "€).");
			
			total += (quantity * price);
		}
		System.out.println("Total = " + total + "€.");
	}
	
	public static void displayAllItemsPerCustomer() throws SQLException{
		Vector<Customer> vecCustomer = CustomerManager.getAllCustomers();
		for(Customer cm : vecCustomer){
			instanceOfItemManager.displayAllItemsFromCustomerName(cm.getFirstName());
		}
	}
	
}
