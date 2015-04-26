package com.seancheey.data.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

import com.seancheey.data.Entity;
import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;

public abstract class Fish extends Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	protected final Pond pond;// the container of fish
	protected transient Image image;// the image of the fish
	protected boolean immobilized = false;// if the fish is fixed
	private double energyUsed, shearY;
	private final double matureWidth, matureHeight;

	// constructor
	public Fish(double width, double height, double x, double y, double vx,
			double vy, Pond pond, Image image, double matureWidth,
			double matureHeight) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.pond = pond;
		this.image = image;
		this.matureWidth = matureHeight;
		this.matureHeight = matureHeight;
	}

	public Fish(double width, double height, double x, double y, double vx,
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
		this.matureWidth = 150;
		this.matureHeight = 75;
	}

	public void setFixed(boolean value) {
		immobilized = value;
	}

	@Override
	public String toString() {
		return "Fish [width=" + width + ", height=" + height + ", x=" + x
				+ ", y=" + y + ", vx=" + vx + ", vy=" + vy + "]";
	}

	// List of getter

	public Pond getPond() {
		return pond;
	}

	// ***Main Algorithm is here!!!***
	public void perform() {
		// grow a bit or die if mature
		if (width < matureWidth)
			width += Math.random() / 10;
		else if (Math.random() < 0.01)
			getPond().remove(this);
		if (height < matureHeight)
			height += Math.random() / 20;
		else if (Math.random() < 0.01)
			getPond().remove(this);
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
		// calculate the energy use
		energyUsed += getVelocity() / 15;
		// calculate the shear
		shearY = Math.sin(energyUsed) * 0.25;

		// propagate in a small probability
		if (width > matureWidth * 0.75 && height > matureHeight * 0.75)
			for (Fish f : pond.getFishes()) {
				if (isCollidedBy(f)) {
					if (Math.random() < 0.001)
						propagate();
					break;
				}
			}
	}

	public void trackOnce(Fish fish) {
		// use mean angle
		double diffx = fish.getX() - x, diffy = fish.getY() - y;
		double vangle = Math.atan2(vy, vx), aimangle = Math.atan2(diffy, diffx);
		if (Math.abs(vangle - aimangle) > Math.PI) {
			vangle = (vangle + aimangle) / 2 + Math.PI;
		} else {
			vangle = (vangle + aimangle) / 2;
		}
		// use mean velocity if not fixed
		double v = getVelocity();
		if (immobilized == false) {
			double diffv = fish.getVelocity();
			v = (v + diffv) / 2;
			// set vx and vy
			vx = Math.cos(vangle) * v;
			vy = Math.sin(vangle) * v;
		}
	}

	public void trackOnce(double px, double py) {
		// use mean angle
		double diffx = px - x, diffy = py - y;
		double vangle = Math.atan2(vy, vx), aimangle = Math.atan2(diffy, diffx);
		double diffangle = Math.abs(vangle - aimangle);
		if (diffangle > Math.PI) {
			vangle = (vangle + aimangle) / 2 + Math.PI;
		} else {
			vangle = (vangle + aimangle) / 2;
		}
		// set vx and vy
		vx = Math.cos(vangle) * getVelocity();
		vy = Math.sin(vangle) * getVelocity();
	}

	public boolean isCollidedBy(Fish fish) {
		if ((Math.abs(fish.getXCenter() - getXCenter()) <= (fish.getWidth() + width) / 2.0)
				&& (Math.abs(fish.getYCenter() - getYCenter()) <= (fish
						.getHeight() + height) / 2.0)) {
			return true;
		}
		return false;
	}

	public void propagate() {
		Fish fish = clone();
		fish.setWidth(20);
		fish.setHeight(10);
		fish.setVx(FishGenerator.randV(5));
		fish.setVy(FishGenerator.randV(5));
		pond.add(fish);
	}

	public void paint(Graphics g) {
		int xcenter = (int) getXCenter();
		int ycenter = (int) getYCenter();
		g.translate(xcenter, ycenter);
		Graphics2D g2 = (Graphics2D) g;
		double angle = Math.atan2(vy, vx);
		g2.shear(0, shearY);
		g2.rotate(angle);
		drawShape(g);
		g2.rotate(-angle);
		g2.shear(0, -shearY);
		g.translate(-xcenter, -ycenter);
	}

	protected void drawShape(Graphics g) {
		if (image == null)
			image = fetchLostImage();
		g.drawImage(image, (int) (-width / 2), (int) (-height / 2),
				(int) (width), (int) (height), null);
	}

	protected abstract Image fetchLostImage();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected abstract Fish clone();

}