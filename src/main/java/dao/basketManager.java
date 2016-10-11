package dao;

import java.sql.SQLException;

import database.MySQL;

public class basketManager {

	
	
	public static int addBasket(String id) throws SQLException{
		String sql = "INSERT INTO basket(id, datecreation) VALUES (?, CURDATE())";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, id);
		return st.executeUpdate();
	}
	
	public static int deleteBasketFromID(String id) throws SQLException{
		String sql = "DELETE FROM basket WHERE id = ?";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		st.setString(1, id);
		return st.executeUpdate();
	}
	
	public static int deleteAllBaskets() throws SQLException{
		String sql = "DELETE FROM basket";
		java.sql.PreparedStatement st = MySQL.getConnection().prepareStatement(sql);
		return st.executeUpdate();
	}
	
}
