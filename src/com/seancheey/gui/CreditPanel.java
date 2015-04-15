package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.seancheey.Main;

public class CreditPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 30);
	// labels to be displayed
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	// back button
	private JButton back=new JButton("back");
	
	CreditPanel() {
		setSize(Main.WIDTH, Main.HEIGHT);
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		labels.add(new JLabel("Credit"));
		labels.add(new JLabel("Creators:"));
		labels.add(new JLabel("Seancheey"));
		labels.add(new JLabel("MaomiHz"));
		labels.add(new JLabel("Contact:"));
		labels.add(new JLabel("adls371@163.com"));
		labels.add(new JLabel("github.com/Seancheey"));
		labels.add(new JLabel("github.com/MaomiHz"));
		labels.add(new JLabel("maomihz.com"));
		labels.add(new JLabel("amao.ninja"));
		for(JLabel label:labels){
			label.setFont(UNIFIED_FONT);
			add(label);
		}
		//set the title of labels to be bigger
		labels.get(0).setFont(new Font("serif",Font.BOLD,40));
		//add back button
		back.addActionListener(this);
		add(back);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Main.controlFrame.switchPanel(this, new Menu());	
	}
}
