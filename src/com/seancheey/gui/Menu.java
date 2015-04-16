package com.seancheey.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.seancheey.Main;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Container of all the components
	private ArrayList<JComponent> components = new ArrayList<JComponent>();
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.BOLD, 35);

	public Menu() {
		setSize(Main.WIDTH, Main.HEIGHT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//add all needed components to the container
		components.add(new JLabel(Main.NAME));
		components.add(new JButton("New"));
		components.add(new JButton("Read"));
		components.add(new JButton("Credit"));
		//unify the font of components and add to this panel
		for (JComponent c : components) {
			if (c instanceof JButton) {
				((JButton) c).setFont(UNIFIED_FONT);
				((JButton) c).addActionListener(this);
			} else if (c instanceof JLabel) {
				((JLabel) c).setFont(UNIFIED_FONT);
			}
			add(c);
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			JButton b = (JButton) a.getSource();
			switch (b.getText()) {
			case "New":
				Main.controlFrame.switchPanel(this, new FishSelectPanel(Main.WIDTH,Main.HEIGHT,null));
				break;
			case "Read":
				// unimplemented
				break;
			case "Credit":
				Main.controlFrame.switchPanel(this, new CreditPanel());
				break;
			}
		}
	}
}
