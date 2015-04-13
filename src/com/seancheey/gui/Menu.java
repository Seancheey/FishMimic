package com.seancheey.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.seancheey.Main;

import java.awt.event.*;
import java.util.ArrayList;

public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JComponent> components;//Container of all components

	public Menu() {
		setSize(Main.WIDTH, Main.HEIGHT);
		components.add(new JLabel(Main.NAME));
		components.add(new JButton("New"));
		components.add(new JButton("Read"));
		components.add(new JButton("Credit"));
		for (int i = 0; i < components.size(); i++) {
			this.add(components.get(i));
			if (components.get(i) instanceof JButton) {
				((JButton) components.get(i)).addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			JButton b = (JButton) a.getSource();
			switch (b.getText()) {
			case "New":
				// unimplemented
				break;
			case "Read":
				// unimplemented
				break;
			case "Credit":
				// unimplemented
				break;
			}
		}
	}
}
