package ecommerce;

import database.MySQL;
import gui.BasketContentGUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MySQL.setConnection();
		
		BasketContentGUI bc = new BasketContentGUI();
		bc.setVisible(true);
	
		//ceci a fonctionné
		//MySQL.closeConnection();
		
	}

}
