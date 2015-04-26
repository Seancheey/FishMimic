package com.seancheey.gui.gamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.seancheey.data.FishGenerator;
import com.seancheey.data.Player;
import com.seancheey.data.Pond;
import com.seancheey.data.entity.Fish;

public class FishMouseListener implements MouseListener, MouseMotionListener {
	private Fish draggedFish;
	private Player player;
	private Pond pond;
	private PondPanel pondPanel;

	public FishMouseListener(PondPanel pondPanel) {
		this.pondPanel = pondPanel;
		pond = pondPanel.getPond();
		player = pond.getPlayer();
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
		if (e.getButton() == MouseEvent.BUTTON1
				&& e.getComponent() == pondPanel && draggedFish != null) {
			if (e.getX() < e.getComponent().getWidth()) {
				draggedFish.setFixed(false);
				draggedFish.setVx(0);
				draggedFish.setVy(0);
				draggedFish = null;
			} else {
				player.earnMoney((int) ((draggedFish.getWidth() + draggedFish
						.getHeight()) / 2));
				pond.remove(draggedFish);
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
		if (e.getButton() == MouseEvent.BUTTON3 && player.getMoney() > 10) {
			player.spendMoney(100);
			pond.getFishes().add(
					new FishGenerator(pond).generateRandom(e.getX()
							- FishGenerator.DEFAULT_WIDTH / 2, e.getY()
							- FishGenerator.DEFAULT_HEIGHT / 2));
		}
	}
}
