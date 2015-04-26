package com.seancheey.gui.gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class PondPanel extends JPanel implements ActionListener {
	/*
	 * 
	 */
	private static final long serialVersionUID = 2L;
	// pond that contain all fishes
	private Pond pond;
	// the background image
	private transient Image background = ImagePond.get("background - sea");
	// thread to operate fish's movement
	private Timer timer = new Timer(15, this);

	// constructor
	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		if (pond == null)
			pond = new Pond(getWidth(), getHeight());
		timer.start();
	}

	public void stopRefreshing() {
		timer.stop();
	}

	/*
	 * @return the pond
	 */
	public Pond getPond() {
		return pond;
	}

	/**
	 * @param pond
	 *            the pond to set
	 */
	public void setPond(Pond pond) {
		this.pond = pond;
	}

	@Override
	public Dimension getPreferredSize() {
		return getParent().getSize();
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(200, 200);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		// resize the pond to match the size of panel
		pond.resize(getSize());
		// Keep painting
		pond.paint(g);
		// paint the money
		g.drawString(String.valueOf(pond.getPlayer().getMoney()), 50, 50);
		repaint();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			pond.nextMove();
		} catch (NullPointerException nulle) {
		}
	}
}
