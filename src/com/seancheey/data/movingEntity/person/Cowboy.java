package com.seancheey.data.movingEntity.person;

import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Person;
import com.seancheey.interfaces.Container;
import com.seancheey.source.PersonPond;

public class Cowboy extends Person {
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
	public void performNext() {
		x += vx;
		y += vy;
	}

}
