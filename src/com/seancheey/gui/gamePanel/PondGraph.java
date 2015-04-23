package com.seancheey.gui.gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.seancheey.data.Pond;

public class PondGraph extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// delay between every two refreshing event
	private static int delay = 5;
	// pond that contain all fishes
	private final Pond pond;
	// the background image
	private Image background = Toolkit.getDefaultToolkit().getImage(
			"res/sea.jpg");

	// constructor
	public PondGraph(Pond pond) {
		super();
		this.pond = pond;
	}

	@Override
	public Dimension getPreferredSize() {
		return getParent().getSize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		// resize the pond to match the size of panel
		pond.resize(getSize());
		// Keep updating
		pond.nextMove();
		pond.paint(g);
		try {
			Thread.sleep(PondGraph.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
}
