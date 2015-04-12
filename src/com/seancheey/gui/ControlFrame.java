package com.seancheey.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.seancheey.Main;

public class ControlFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlFrame() {
		super(Main.NAME);
		setSize(Main.WIDTH,Main.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(false);
	}
}
