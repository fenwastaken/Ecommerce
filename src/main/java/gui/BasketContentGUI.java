package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import dao.Customer;
import dao.CustomerManager;
import dao.Item;
import dao.ItemManager;
import dao.instanceOfItemManager;

public class BasketContentGUI extends JFrame{

	JComboBox<String> cbCustomer = new JComboBox<String>();
	JComboBox<String> cbItem = new JComboBox<String>();
	
	JButton btAdd = new JButton("Add");
	JButton btDelete = new JButton("Del");
	
	JTextArea taCustomer = new JTextArea();
	
	JTextField tfQuantity = new JTextField();
	
	JPanel panContent = new JPanel();
	JScrollPane spanItems = new JScrollPane(panContent);
	
	JMenuItem miItem = new JMenuItem("Items");
	JMenuItem miCustomers = new JMenuItem("Customers");
	
	
	
	public BasketContentGUI(){
		this.setTitle("Ecommerce - Main");
		this.setSize(800, 620);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initControles();
	}
	
	public void initControles(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Manage");
		
		menuBar.add(menu);
		
		menu.add(miItem);
		menu.add(miCustomers);
		
		this.setJMenuBar(menuBar);
		
		
		JPanel zoneClient = (JPanel) this.getContentPane();
		//zoneClient.setLayout(new BorderLayout());
		
		JPanel panLeft = new JPanel();
		JPanel panRight = new JPanel();
		
		JPanel panCenter = new JPanel();
		panCenter.add(panLeft);
		panCenter.add(panRight);
		
		Dimension d= new Dimension(380, 550);
		panLeft.setPreferredSize(d);
		panRight.setPreferredSize(d);
		
		
		panLeft.setBorder(BorderFactory.createEtchedBorder());
		panRight.setBorder(BorderFactory.createEtchedBorder());
		
		zoneClient.add(panCenter);
		
		panLeft.setLayout(new BoxLayout(panLeft, BoxLayout.Y_AXIS));
		panRight.setLayout(new BoxLayout(panRight, BoxLayout.Y_AXIS));
		
		panLeft.setAlignmentX(LEFT_ALIGNMENT);
		panLeft.add(cbCustomer);
		panLeft.add(taCustomer);
		taCustomer.setEditable(false);
		
		//panRight
		
		
		panRight.add(spanItems);
		spanItems.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel PanContainer = new JPanel();
		PanContainer.setPreferredSize(new Dimension(380, 80));
		spanItems.setPreferredSize(new Dimension(380, 400));
		panRight.add(PanContainer);
		
		
		
		JPanel panCombo = new JPanel();
		
		panCombo.add(cbItem);
		panCombo.add(new JLabel("qqty:"));
		panCombo.add(tfQuantity);
		PanContainer.add(panCombo);
		
		JPanel panButtons = new JPanel();
		
		panButtons.add(btAdd);
		panButtons.add(btDelete);
		
		PanContainer.add(panButtons);
		
		cbItem.setPreferredSize(new Dimension(200, 30));
		tfQuantity.setPreferredSize(new Dimension(100, 30));
		panCombo.setPreferredSize(new Dimension(380, 40));
		panButtons.setPreferredSize(new Dimension(380, 70));
		
		panContent.setLayout(new BoxLayout(panContent, BoxLayout.Y_AXIS));
		
		//listeners and shit
		refreshCustomers();
		refreshItems();
		displayCustomer();
		
		cbCustomer.addItemListener(new appItemListener());
		btAdd.addActionListener(new appActionListener());
		btDelete.addActionListener(new appActionListener());
		miCustomers.addActionListener(new appActionListener());
		miItem.addActionListener(new appActionListener());
		
		
	}
	
	public void generateLines(String name){
		panContent.removeAll();
		try {
			Vector<String[]> vecTab = instanceOfItemManager.getAllItemsFromName(name);
			int total = 0;
			for(String[] tab : vecTab){
				setLine(tab[0] + ": " + tab[1] + " * " + tab[2] + "€ = " + tab[3] + "€");
				total += Integer.parseInt(tab[3]);
			}
			setLine("Total = " + total + "€");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panContent.revalidate();
		panContent.repaint();
		
	}
	
	public void setLine(String str){
		JLabel lab = new JLabel();
		JPanel panLab = new JPanel();
		panLab.setLayout(new FlowLayout(FlowLayout.LEFT));
		panLab.setPreferredSize(new Dimension(375, 30));
		panLab.setMaximumSize(new Dimension(375, 30));
		
		lab.setText(str);
		panLab.setBorder(BorderFactory.createEtchedBorder());
		panLab.add(lab);
		panContent.add(panLab);
		System.out.println(str);
	}
	
	public void refreshCustomers(){
		try {
			cbCustomer.removeAllItems();
			Vector<Customer> vecCustomer = CustomerManager.getAllCustomers();
			for(Customer cm : vecCustomer){
				cbCustomer.addItem(cm.getFirstName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshItems(){
		try {
			cbItem.removeAllItems();
			Vector<Item> vecItem = ItemManager.getAllItems();
			for(Item itm : vecItem){
				cbItem.addItem(itm.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayCustomer(){
		if(cbCustomer.getItemCount() > 0){
			String selected = cbCustomer.getSelectedItem().toString();
			try {
				Customer cm = CustomerManager.getCustomerFromName(selected);
				taCustomer.setText("");
				taCustomer.append("First name: " + cm.getFirstName());
				taCustomer.append("\nLast name: " + cm.getLastName());
				taCustomer.append("\nGender " + cm.getGender());
				taCustomer.append("\nAge: " + cm.getAge());
				taCustomer.append("\nAddress: " + cm.getAddress());
				taCustomer.append("\nEmail: " + cm.getEmail());
				generateLines(selected);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class appItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == cbCustomer){
				displayCustomer();
			}
		}
		
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == btAdd){
				if(!tfQuantity.getText().isEmpty()){
					String customer = cbCustomer.getSelectedItem().toString();
					String item = cbItem.getSelectedItem().toString();
					int quantity = Integer.parseInt(tfQuantity.getText());
					try {
						instanceOfItemManager.addItemFromNames(customer, item, quantity);
						generateLines(cbCustomer.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			if(e.getSource() == btDelete){
				try {
					instanceOfItemManager.deleteItemFromNames(cbItem.getSelectedItem().toString(), cbCustomer.getSelectedItem().toString());
					generateLines(cbCustomer.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			if(e.getSource() == miCustomers){
				CustomerGUI cu = new CustomerGUI(BasketContentGUI.this);
				cu.setVisible(true);
			}
			
			if(e.getSource() == miItem){
				ItemGUI it = new ItemGUI(BasketContentGUI.this);
				it.setVisible(true);
			}
		}
		
	}
	
}
