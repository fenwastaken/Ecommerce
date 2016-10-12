package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.util.jar.Attributes.Name;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.Customer;
import dao.CustomerManager;
import dao.TestCustomerManager;

public class CustomerGUI extends JDialog{

	BasketContentGUI basket = null;
	
	Line liFirstName = new Line("First name: ");
	Line liLastName = new Line("Last name: ");
	Line liGender = new Line("Gender: ");
	Line liAge = new Line("Age: ");
	Line liAddress = new Line("Address: ");
	Line liEmail = new Line("Email: ");

	JButton btAdd = new JButton("ADD");
	JButton btDel = new JButton("Delete");

	JLabel labReturn = new JLabel();

	JComboBox<String> cbDel = new JComboBox<String>();

	public CustomerGUI(BasketContentGUI basket){
		this.setSize(400, 380);
		this.setTitle("Ecommerce - Customer");
		this.setModal(true);
		this.setLocationRelativeTo(basket);
		this.basket = basket;
		
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	basket.refreshCustomers();
		    }
		});
		
		
		initControles();
	}
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BoxLayout(zoneClient, BoxLayout.Y_AXIS));



		JPanel panRet = new JPanel();
		panRet.add(btAdd);
		panRet.add(labReturn);
		panRet.setMaximumSize(new Dimension(400, 40));
		labReturn.setMinimumSize(new Dimension(250, 30));
		labReturn.setPreferredSize(new Dimension(250, 30));

		zoneClient.add(liFirstName);
		zoneClient.add(liLastName);
		zoneClient.add(liGender);
		zoneClient.add(liAge);
		zoneClient.add(liAddress);
		zoneClient.add(liEmail);
		zoneClient.add(panRet);

		JPanel panDelete = new JPanel();

		refreshCbDel();

		panDelete.add(cbDel);
		panDelete.add(btDel);
		zoneClient.add(panDelete);

		btAdd.addActionListener(new appActionListener());
		btDel.addActionListener(new appActionListener());
	}

	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if(e.getSource() == btAdd){
				String fname = liFirstName.getTf().getText();
				String lname = liLastName.getTf().getText();
				int age = Integer.parseInt(liAge.getTf().getText());
				String address = liAddress.getTf().getText();
				String gender = liGender.getTf().getText();
				String email = liEmail.getTf().getText();

				try {
					CustomerManager.addCustomer(new Customer(null, fname, lname, gender, age, address, email, null));
					labReturn.setText(fname + " was added !");

					liFirstName.getTf().setText("");
					liLastName.getTf().setText("");
					liAge.getTf().setText("");
					liAddress.getTf().setText("");
					liGender.getTf().setText("");
					liEmail.getTf().setText("");
					refreshCbDel();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					labReturn.setText("Couldn't add Customer");
				}
			}

			if(e.getSource() == btDel){
				try {
					String name = cbDel.getSelectedItem().toString();
					CustomerManager.deleteCustomerFromName(name);
					labReturn.setText(name + " was deleted!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					labReturn.setText("Couldn't delete");
				}
				refreshCbDel();
			}

		}

	}

	public void refreshCbDel(){

		cbDel.removeAllItems();

		Vector<Customer> vecCustomers = null;
		try {
			vecCustomers = CustomerManager.getAllCustomers();
			for(Customer cstm : vecCustomers){
				cbDel.addItem(cstm.getFirstName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
