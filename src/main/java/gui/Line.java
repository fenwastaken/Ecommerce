package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Line extends JPanel{

	public JLabel lab = new JLabel();
	public JTextField tf = new JTextField();
	public JButton bt = new JButton();
	
	public Line(String lab, String bt){
		
		this.lab.setText(lab);
		this.bt.setText(bt);
		
		this.lab.setPreferredSize(new Dimension(30, 100));
		this.tf.setPreferredSize(new Dimension(30, 200));
		this.bt.setPreferredSize(new Dimension(30, 50));
		this.setPreferredSize(new Dimension(50, 400));
		this.add(this.lab);
		this.add(this.tf);
		this.add(this.bt);
		
	}

	public Line(String lab){
		
		this.lab.setText(lab);

		
		this.lab.setPreferredSize(new Dimension(100, 30));
		this.tf.setPreferredSize(new Dimension(200, 30));
		this.bt.setPreferredSize(new Dimension(30, 50));
		this.setPreferredSize(new Dimension(400, 40));
		
		this.setMaximumSize(new Dimension(400, 40));
		this.setMinimumSize(new Dimension(400, 40));
		
		this.add(this.lab);
		this.add(this.tf);
		
	}
	
	public JLabel getLab() {
		return lab;
	}

	public void setLab(JLabel lab) {
		this.lab = lab;
	}

	public JTextField getTf() {
		return tf;
	}

	public void setTf(JTextField tf) {
		this.tf = tf;
	}

	public JButton getBt() {
		return bt;
	}

	public void setBt(JButton bt) {
		this.bt = bt;
	}
	
	
	
}
