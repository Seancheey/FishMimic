package com.seancheey.data;

import java.io.Serializable;

import com.seancheey.HasImage;
import com.seancheey.Movable;

public abstract class Entity implements Serializable, HasImage, Movable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double width, height, x, y, vx, vy;// the location and velocity

	public Entity(double width, double height, double x, double y, double vx,
			double vy) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getVelocity() {
		return Math.sqrt(vx * vx + vy * vy);
	}

	public double getXCenter() {
		return x + width / 2;
	}

	public double getYCenter() {
		return y + height / 2;
	}

}
