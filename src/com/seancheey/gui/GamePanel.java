package com.seancheey.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.seancheey.data.Map;
import com.seancheey.data.map.MeadowMap;
import com.seancheey.interfaces.GUIContainer;

public class GamePanel extends JPanel implements ActionListener,
		MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private Map map;
	private transient BufferedImage bufferImage;
	private Timer timer = new Timer(20, this);

	public GamePanel(GUIContainer guiContainer) {
		this(guiContainer, new MeadowMap(1500, 900));
	}

	public GamePanel(GUIContainer guiContainer, Map map) {
		super();
		this.map = map;
		bufferImage = new BufferedImage(map.getWidth(), map.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);
		addMouseMotionListener(this);
		addMouseListener(this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		map.performNext();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(map.getWidth(), map.getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		map.mouseClicked(e);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		map.mouseDragged(e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		map.mouseEntered(e);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		map.mouseExited(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		map.mouseMoved(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		map.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		map.mouseReleased(e);

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
