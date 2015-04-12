package com.seancheey.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.seancheey.Main;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton[] buttons;
	public Menu() {
		setSize(Main.WIDTH, Main.HEIGHT);
		buttons=new JButton[1];
		buttons[0]=new JButton("Rectangle Fish");
		
	}

}
