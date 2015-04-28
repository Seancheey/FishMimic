package com.seancheey.data.entity;

import java.awt.Image;

import com.seancheey.Container;
import com.seancheey.data.Entity;
import com.seancheey.data.MovingEntity;

public abstract class Person extends MovingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Person(double width, double height, double x, double y, Image image,
			Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, image, container, vx, vy);
	}

}
