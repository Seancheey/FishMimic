package com.seancheey.gui.gamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;
import com.seancheey.data.entity.Fish;

public class FishMouseListener implements MouseListener, MouseMotionListener {
	private Fish draggedFish;
	private Pond pond;

	public FishMouseListener(Pond pond) {
		super();
		this.pond = pond;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			pond.getFishes().add(
					new FishGenerator(pond).generateRandom(e.getX()
							- FishGenerator.DEFAULT_WIDTH / 2, e.getY()
							- FishGenerator.DEFAULT_HEIGHT / 2));
	}
}
