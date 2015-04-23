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
	private static int delay = 5;
	// pond that contain all fishes
	private final Pond pond;
	// the background image
	private transient Image background = ImagePond.get("background - sea");
	// the fish that is being dragging
	private Fish draggedFish = null;

	// constructor
	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		addMouseListener(this);
		addMouseMotionListener(this);
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
			Thread.sleep(PondPanel.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		draggedFish = pond.getFishAt(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		draggedFish = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (draggedFish != null) {
			draggedFish.setVx(0);
			draggedFish.setVy(0);
			draggedFish.setX(e.getX() - draggedFish.getWidth() / 2);
			draggedFish.setY(e.getY() - draggedFish.getHeight() / 2);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
