package com.seancheey.data.entity;

import java.awt.Image;

import com.seancheey.data.Entity;

public abstract class Person extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient Image image;
	protected double rotation;

	public Person(double width, double height, double x, double y, double vx,
			double vy, Image image) {
		super(width, height, x, y, vx, vy);
		this.image = image;
	}

}
