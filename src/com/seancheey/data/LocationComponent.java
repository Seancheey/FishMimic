package com.seancheey.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class LocationComponent extends JComponent implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Image image;
	private int imageWidth, imageHeight;
	private String name;

	public LocationComponent(Image image, int imageWidth, int imageHeight,
			String name) {
		super();
		this.image = image;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.name = name;
		JButton reactionButton = new JButton(" ");
		reactionButton.setBorderPainted(false);
		reactionButton.setRequestFocusEnabled(false);
		reactionButton.setBackground(new Color(0, 0, 0, 0));
		reactionButton.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, imageWidth, imageHeight, this);
		g.drawString(name, 0, 0);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(imageWidth, imageHeight);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
