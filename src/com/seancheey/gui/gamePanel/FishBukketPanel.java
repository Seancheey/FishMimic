package com.seancheey.gui.gamePanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class FishBukketPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FishBukketPanel() {
		setBackground(Color.YELLOW);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(100, 100);
	}

	@Override
	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(200, 2000);
	}
}