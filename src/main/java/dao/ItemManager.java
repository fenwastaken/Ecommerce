package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Vector;

import database.MySQL;

public class ItemManager {

	public static int addItem(Item item) throws SQLException{
		String sql = "INSERT INTO Item(id, name, description, price) VALUES(?, ?, ?, ?)";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, UUID.randomUUID().toString());
		st.setString(2, item.getName());
		st.setString(3, item.getDescription());
		st.setInt(4, item.getPrice());
		return st.executeUpdate();
	}

	public static int deleteAllItems() throws SQLException{
		String sql = "DELETE FROM Items";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		return st.executeUpdate();

	}

	public static int deleteItemsFromName(String name) throws SQLException{
		String sql = "DELETE FROM Item WHERE LOWER(name) = ?";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name.toLowerCase());
		return st.executeUpdate();
	}

	public static String getIdFromName(String name) throws SQLException{
		String sql = "SELECT id FROM item WHERE LOWER(name) = ?";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name.toLowerCase());
		ResultSet rs = st.executeQuery();
		String id = null;
		if(rs.next()){
			id = rs.getString("id");
		}
		//System.out.println("return for " + name + ", result = " + id);
		return id;
	}

	public static Vector<Item> getAllItems() throws SQLException{
		String sql = "SELECT * FROM Item ORDER BY name";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		Item item = null;
		Vector<Item> vecItem = new Vector<Item>();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			int price = rs.getInt("price");
			item = new Item(id, name, description, price);
			vecItem.add(item);
		}
		return vecItem;
	}

	public static void displayAllItems(Vector<Item> vecItem){
		int i = 0;
		for(Item itm : vecItem){
			i++;
			System.out.println("Item " + i + ": " + itm.toString());
		}
	}

	public static int getPriceFromName(String name) throws SQLException{
		String sql = "SELECT price FROM Item WHERE LOWER(name) = ?";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		int price = 0;
		if(rs.next()){
			price = rs.getInt("price");
		}
		return price;
	}
}
