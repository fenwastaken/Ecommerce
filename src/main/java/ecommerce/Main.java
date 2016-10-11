package ecommerce;

import gui.BasketContentGUI;
import gui.CustomerGUI;
import gui.ItemGUI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import dao.TestCustomerManager;
import dao.instanceOfItemManager;
import database.MySQL;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MySQL.setConnection();

		//lol 
		
		BasketContentGUI bc = new BasketContentGUI();
		bc.setVisible(true);
	
		
		//MySQL.closeConnection();
		
	}

}
