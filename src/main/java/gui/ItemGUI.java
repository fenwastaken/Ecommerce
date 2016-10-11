package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.Item;
import dao.ItemManager;

public class ItemGUI extends JDialog{

	BasketContentGUI basket = null;
	
	Line liName = new Line("Item name: ");
	Line liDescription = new Line("Description: ");
	Line liPrice = new Line("Price: ");

	JButton btAdd = new JButton("ADD");
	JButton btDel = new JButton("Delete");

	JLabel labReturn = new JLabel();

	JComboBox<String> cbDel = new JComboBox<String>();

	public ItemGUI(BasketContentGUI basket){
		this.setSize(400, 280);
		this.setTitle("Ecommerce - Item");
		this.setModal(true);
		this.setLocationRelativeTo(basket);
		this.basket = basket;
		
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	basket.refreshItems();
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
		

		zoneClient.add(liName);
		zoneClient.add(liDescription);
		zoneClient.add(liPrice);
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
				String name = liName.getTf().getText();
				String description = liName.getTf().getText();
				try{
				int price = Integer.parseInt(liPrice.getTf().getText());
				Item item = new Item(null, name, description, price);
				labReturn.setText(name + " was added !");
				ItemManager.addItem(item);
				
				liName.getTf().setText("");
				liDescription.getTf().setText("");
				liPrice.getTf().setText("");
				refreshCbDel();
				}
				catch(NumberFormatException num){
					labReturn.setText(liPrice.getTf().getText() + " isn't a number, retard!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					labReturn.setText("Couldn't add Item");
				}
			}

			if(e.getSource() == btDel){
				try {
					String name = cbDel.getSelectedItem().toString();
					ItemManager.deleteItemsFromName(name);
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

		Vector<Item> vecItems = null;
		try {
			vecItems = ItemManager.getAllItems();
			for(Item itm : vecItems){
				cbDel.addItem(itm.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
