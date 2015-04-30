package com.seancheey.data.movingEntity.person;

import java.awt.event.MouseEvent;

import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Person;
import com.seancheey.interfaces.Container;
import com.seancheey.interfaces.MouseControllable;
import com.seancheey.source.PersonPond;

public class Cowboy extends Person implements MouseControllable {
	private static final long serialVersionUID = 1L;

	public Cowboy(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, PersonPond.COWBOY, container, vx, vy);
	}

	@Override
	public void fetchLostImage() {
		image = PersonPond.COWBOY;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		vx = 2 * (Math.random() - 0.5);
		vy = 2 * (Math.random() - 0.5);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX() - width / 2;
		y = e.getY() - height / 2;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
