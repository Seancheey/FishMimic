package com.seancheey.data;

import java.awt.Image;

import com.seancheey.Container;

public abstract class MovingEntity extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double vx, vy;

	public MovingEntity(double width, double height, double x, double y,
			Image image, Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, image, container);
		this.vx = vx;
		this.vy = vy;
	}

	public double getVelocity() {
		return Math.sqrt(vx * vx + vy * vy);
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

}
