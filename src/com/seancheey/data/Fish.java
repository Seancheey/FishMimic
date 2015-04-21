package com.seancheey.data;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Fish implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int width, height;// the size of fish
	protected double x, y, vx, vy;// the location and velocity of fish1`
	protected final Pond pond;// the container of fish
	protected Color color;// color displayed of the fish

	// List of constructor
	public Fish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.pond = pond;
		this.color = color;
	}

	public Fish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.pond = pond;
		this.color = FishGenerator.randColor();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	@Override
	public String toString() {
		return "Fish [width=" + width + ", height=" + height + ", x=" + x
				+ ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", color=" + color
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
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

	public Pond getPond() {
		return pond;
	}

	// ***Main Algorithm is here!!!***
	protected void perform() {
		// have a move
		x += vx;
		y += vy;
		// Touch the wall to reflect
		if (x > getPond().getWidth() || x < 0) {
			vx *= -0.92;
		}
		if (y > getPond().getHeight() || y < 0) {
			vy *= -0.92;
		}
		// Haha, asean!
		// to prevent the ball from sticking into wall
		if (x > getPond().getWidth()) {
			x = getPond().getWidth() - width;
		}
		if (y > getPond().getHeight()) {
			y = getPond().getHeight() - height;
		}
		if (x < 0) {
			x = width;
		}
		if (y < 0) {
			y = height;
		}
	}

	protected void paint(Graphics g) {
		g.setColor(color);
	}
}
