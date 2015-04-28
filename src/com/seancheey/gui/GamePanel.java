package com.seancheey.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.seancheey.data.Map;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	private BufferedImage bufferImage = new BufferedImage(1000, 1000,
			BufferedImage.TYPE_3BYTE_BGR);

	public GamePanel(Map map) {
		super();
		this.map = map;
	}

	private void paintOnBufferedImage() {
		bufferImage.flush();
		map.paint(bufferImage.getGraphics());
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintOnBufferedImage();
		g.drawImage(bufferImage, 0, 0, getWidth(), getHeight(), this);
		repaint();
	}
}
