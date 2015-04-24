package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

public abstract class Fish implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int width, height;// the size of fish
	protected double x, y, vx, vy;// the location and velocity of fish1`
	protected final Pond pond;// the container of fish
	protected transient Image image;// the image of the fish
	protected boolean immobilized = false;// if the fish is fixed

	// constructor
	public Fish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Image image) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.pond = pond;
		this.image = image;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void setFixed(boolean value) {
		immobilized = value;
	}

	@Override
	public String toString() {
		return "Fish [width=" + width + ", height=" + height + ", x=" + x
				+ ", y=" + y + ", vx=" + vx + ", vy=" + vy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		long temp;
		temp = Double.doubleToLongBits(vx);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(vy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + width;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fish other = (Fish) obj;
		if (height != other.height)
			return false;
		if (Double.doubleToLongBits(vx) != Double.doubleToLongBits(other.vx))
			return false;
		if (Double.doubleToLongBits(vy) != Double.doubleToLongBits(other.vy))
			return false;
		if (width != other.width)
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	// List of getter
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
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

	public Pond getPond() {
		return pond;
	}

	// ***Main Algorithm is here!!!***
	protected void perform() {
		// to prevent the ball from sticking into wall
		if (x > getPond().getWidth()) {
			x = getPond().getWidth() - width;
			vx = -vx;
		}
		if (y > getPond().getHeight()) {
			y = getPond().getHeight() - height;
			vy = -vy;
		}
		if (x < 0) {
			x = width;
			vx = -vx;
		}
		if (y < 0) {
			y = height;
			vy = -vy;
		}
		// have a move
		if (!immobilized) {
			x += vx;
			y += vy;
		}
	}

	public void trackOnce(Fish fish) {
		// ues mean angle
		double diffx = fish.getX() - x, diffy = fish.getY() - y;
		double vangle = Math.atan2(vy, vx), aimangle = Math.atan2(diffy, diffx);
		double diffangle = Math.abs(vangle - aimangle);
		if (diffangle > Math.PI) {
			vangle = (vangle + aimangle) / 2 + Math.PI;
		} else {
			vangle = (vangle + aimangle) / 2;
		}
		// use mean velocity is not fixed
		double v = getVelocity();
		if (immobilized == false) {
			double diffv = fish.getVelocity();
			v = (v + diffv) / 2;
		}
		// set vx and vy
		vx = Math.cos(vangle) * v;
		vy = Math.sin(vangle) * v;
	}

	public boolean isCollidedBy(Fish fish) {
		if ((Math.abs(fish.getXCenter() - getXCenter()) <= (fish.getWidth() + width) / 2.0)
				&& (Math.abs(fish.getYCenter() - getYCenter()) <= (fish
						.getHeight() + height) / 2.0))
			return true;
		else
			return false;
	}

	public void paint(Graphics g) {
		int xcenter = (int) getXCenter();
		int ycenter = (int) getYCenter();
		g.translate(xcenter, ycenter);
		Graphics2D g2 = (Graphics2D) g;
		double angle = Math.atan2(vy, vx);
		g2.rotate(angle);
		drawShape(g);
		g2.rotate(-angle);
		g.translate(-xcenter, -ycenter);
	}

	protected abstract void drawShape(Graphics g);
}
