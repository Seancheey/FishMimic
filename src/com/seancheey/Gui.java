package com.seancheey;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.seancheey.interfaces.GUIContainer;

public class Gui extends JFrame implements GUIContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Gui() {
		super(Main.NAME);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 300));
		setUndecorated(false);
	}

	@Override
	public void switchPanel(JPanel oldPanel, JPanel newPanel) {
		this.getContentPane().remove(oldPanel);
		this.getContentPane().add(newPanel);
		this.setVisible(true);
	}
}
