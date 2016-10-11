package ecommerce;

import java.sql.SQLException;

import dao.Customer;
import dao.CustomerManager;
import dao.TestCustomerManager;
import dao.Item;
import dao.ItemManager;
import dao.basketManager;

public class Test {

	public static void deleteAllCustomers(){
		try {
			CustomerManager.deleteAllCustomers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateCustomers(){
		Customer customer = new Customer(null, "Saika", "Totsuka", "Male", 16, "japan", "saika.totsuka@gmail.com", null);
		Customer customer2 = new Customer(null, "Makoto", "Nanaya", "Female", 17, "Blazblue", "makoto.nanaya@gmail.com", null);
		Customer customer3 = new Customer(null, "Nagisa", "Shiota", "Male", 15, "japan", "shiota.nagisa@gmail.com", null);
		Customer customer4 = new Customer(null, "Ruka", "Urushibara", "Male", 17, "japan", "rukako@gmail.com", null);
		
		try {
			basketManager.addBasket(CustomerManager.addCustomer(customer));
			basketManager.addBasket(CustomerManager.addCustomer(customer2));
			basketManager.addBasket(CustomerManager.addCustomer(customer3));
			basketManager.addBasket(CustomerManager.addCustomer(customer4));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void generateItems(){
		Item item = new Item(null, "patates", "Un sac de patates", 5);
		Item item2 = new Item(null, "chocolat", "Une tablette de chocolat noir", 2);
		Item item3 = new Item(null, "saucisson", "Un fuet catalan", 3);
		Item item4 = new Item(null, "cr�me", "un pot de cr�me fraiche", 1);
		Item item5 = new Item(null, "danettes", "un lot de 4 danettes", 2);
		Item item6 = new Item(null, "steack", "une barquette contenant un steack � griller", 4);
		Item item7 = new Item(null, "�ponges", "un lot de 6 �ponges", 4);
		Item item8 = new Item(null, "raviolis", "une boite de raviolis", 2);
		Item item9 = new Item(null, "sandwich", "un sandwich jambon beurre", 3);
		Item item10 = new Item(null, "nouilles instantann�es", "un paquet de nouilles deshydrat�es", 1);
		
		try {
			ItemManager.addItem(item);
			ItemManager.addItem(item2);
			ItemManager.addItem(item3);
			ItemManager.addItem(item4);
			ItemManager.addItem(item5);
			ItemManager.addItem(item6);
			ItemManager.addItem(item7);
			ItemManager.addItem(item8);
			ItemManager.addItem(item9);
			ItemManager.addItem(item10);
			System.out.println("Items added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
