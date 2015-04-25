package com.seancheey.gui.loadingPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class LoadingComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double mainProgress, subProgress;
	private Color mainColor, subColor;
	private int width, height;
	private int painInterval = 10;

	public LoadingComponent(Color mainColor, Color subColor, int width,
			int height) {
		super();
		this.mainColor = mainColor;
		this.subColor = subColor;
		this.width = width;
		this.height = height;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawArc(0, 0, width, height, 0, (int) (360 * mainProgress));
		g.drawArc(0, 0, width, height, 0, (int) (360 * subProgress));
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void resetLoadingComponent(double mainProgress) {
		this.mainProgress = mainProgress;
	}
}
