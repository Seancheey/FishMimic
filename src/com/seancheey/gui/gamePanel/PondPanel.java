package com.seancheey.gui.gamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.seancheey.data.Fish;
import com.seancheey.data.FishGenerator;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class PondPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// delay between every two refreshing event
	private static int delay = 100;
	// pond that contain all fishes
	private Pond pond;
	// the background image
	private transient Image background = ImagePond.get("background - sea");
	// the fish that is being dragging
	private Fish draggedFish = null;
	// thread to operate fish's movement
	private Thread moveThread = new Thread() {
		public void run() {
			while (true) {
				try {
					pond.nextMove();
				} catch (NullPointerException nulle) {
				}
				try {
					sleep(delay);
				} catch (InterruptedException ie) {
				}
			}
		}
	};

	// constructor
	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		addMouseListener(this);
		addMouseMotionListener(this);
		moveThread.start();
	}

	public void stopRefreshing() {
		try {
			moveThread.interrupt();
		} catch (Exception e) {
		}
	}

	/**
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		// resize the pond to match the size of panel
		pond.resize(getSize());
		// Keep painting
		pond.paint(g);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			pond.getFishes().add(
					new FishGenerator(pond).generateRandom(e.getX(), e.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			draggedFish = pond.getFishAt(e.getX(), e.getY());
			if (draggedFish != null)
				draggedFish.setFixed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (draggedFish != null) {
				draggedFish.setFixed(false);
				draggedFish.setVx(0);
				draggedFish.setVy(0);
				draggedFish = null;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (draggedFish != null) {
			draggedFish.setX(e.getX() - draggedFish.getWidth() / 2);
			draggedFish.setY(e.getY() - draggedFish.getHeight() / 2);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
