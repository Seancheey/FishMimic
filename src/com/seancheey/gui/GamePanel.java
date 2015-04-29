package com.seancheey.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.seancheey.data.Map;
import com.seancheey.data.map.MeadowMap;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Map map;
	private transient BufferedImage bufferImage;
	private Timer timer = new Timer(20, this);

	public GamePanel() {
		this(new MeadowMap(800, 800));
	}

	public GamePanel(Map map) {
		super();
		this.map = map;
		bufferImage = new BufferedImage(map.getWidth(), map.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);
		timer.start();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(map.getWidth(), map.getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		map.performNext();
	}

	@Override
	protected void paintComponent(Graphics g) {
		paintOnBufferedImage();
		g.drawImage(bufferImage, 0, 0, this);
		repaint();
	}

	private void paintOnBufferedImage() {
		bufferImage.flush();
		map.paint(bufferImage.getGraphics());
	}
}
