package com.seancheey.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.seancheey.Main;

public class ControlFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlFrame() {
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
