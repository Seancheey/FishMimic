package com.seancheey.data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Map extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Image background;
	private ArrayList<LocationComponent> locations = new ArrayList<LocationComponent>();

	public Map(Image background, ArrayList<LocationComponent> locations) {
		super();
		this.background = background;
		this.locations = locations;
		for (LocationComponent location : locations) {
			add(location);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#printComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(background.getWidth(null),
				background.getHeight(null));
	}
}
