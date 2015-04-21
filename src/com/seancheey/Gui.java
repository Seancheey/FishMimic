package com.seancheey;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Gui() {
		super(Main.NAME);
		setSize(Main.WIDTH, Main.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 300));
		setUndecorated(false);
	}

	public void switchPanel(JPanel oldPanel, JPanel newPanel) {
		this.getContentPane().remove(oldPanel);
		this.getContentPane().add(newPanel);
		this.setVisible(true);
	}
}