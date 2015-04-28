package com.seancheey.data.entity;

import java.awt.Image;
import com.seancheey.Container;
import com.seancheey.data.Entity;

public abstract class Building extends Entity {
	private static final long serialVersionUID = 1L;

	public Building(double width, double height, double x, double y,
			Image image, Container<Entity> container) {
		super(width, height, x, y, image, container);
	}

	@Override
	public void performNext() {
	}

}
