package com.seancheey.data.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;
import java.util.Iterator;

import com.seancheey.data.Entity;
import com.seancheey.data.Pond;

public abstract class Fish extends Entity implements Serializable {
	private static final long serialVersionUID = 2L;
	/** the container of fish */
	protected Pond pond;
	/** the image of the fish */
	protected transient Image image;
	/** if the fish is fixed */
	protected boolean immobilized = false;
	/** the energy used by the fish(used to calculate shear) */
	private double energyUsed;
	/** the shear that distort the fish to make it seems move */
	private double shearY;
	/** the size of mature fish */
	private final double matureWidth, matureHeight;

	/** simplified constructor */
	public Fish(double width, double height, double x, double y, double vx,
			double vy, Pond pond, Image image) {
		this(width, height, x, y, vx, vy, pond, image, 150, 75);
	}

	/** origin constructor */
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
		this.matureWidth = matureWidth;
		this.matureHeight = matureHeight;
	}

	@Override
	public abstract Fish clone();

	private void correctShear() {
		// calculate the energy use
		energyUsed += getVelocity() / 15;
		// calculate the shear
		shearY = Math.sin(energyUsed) * 0.25;
	}

	private void detectCollisionWithWall() {
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
	}

	/** draw the image of fish by the graphics */
	protected void drawShape(Graphics g) {
		if (image == null)
			image = fetchLostImage();
		g.drawImage(image, (int) (-width / 2), (int) (-height / 2),
				(int) (width), (int) (height), null);
	}

	/** the fetch the lost image (used in recovered serialized object) */
	protected abstract Image fetchLostImage();

	public Pond getPond() {
		return pond;
	}

	/** return the price of selling the fish */
	public int getPrice() {
		return (int) ((width + height) / 2);
	}

	// grow a bit or die if mature
	private void grow() {
		if (width <= matureWidth || height <= matureHeight) {
			width += Math.random() / 10;
			height += Math.random() / 20;
		} else {
			if (Math.random() < 0.01) {
				getPond().remove(this);
			}
		}
	}

	/** detect if the fish collides with another fish */
	public boolean isCollidedBy(Fish fish) {
		if ((Math.abs(fish.getXCenter() - getXCenter()) <= (fish.getWidth() + width) / 2.0)
				&& (Math.abs(fish.getYCenter() - getYCenter()) <= (fish
						.getHeight() + height) / 2.0)) {
			return true;
		}
		return false;
	}

	/** paint the image on the graph */
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

	/** perform the next movement */
	public void perform() {
		grow();
		detectCollisionWithWall();
		// have a move
		if (!immobilized) {
			x += vx;
			y += vy;
		}
		correctShear();
		if (willPropagate())
			propagate();
		;
	}

	/** add a new small fish to the container */
	public void propagate() {
		Fish fish = clone();
		fish.setWidth(20);
		fish.setHeight(10);
		fish.setVx(Pond.randV(5));
		fish.setVy(Pond.randV(5));
		pond.add(fish);
	}

	public void setAngularVelocity(double velocity, double angle) {
		vx = Math.cos(angle) * velocity;
		vy = Math.sin(angle) * velocity;
	}

	public void setFixed(boolean value) {
		immobilized = value;
	}

	public void setPond(Pond pond) {
		this.pond = pond;
	}

	@Override
	public String toString() {
		return "Fish [width=" + width + ", height=" + height + ", x=" + x
				+ ", y=" + y + ", vx=" + vx + ", vy=" + vy + "]";
	}

	/** move towards the point directly once */
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
		setAngularVelocity(getVelocity(), vangle);
	}

	/** move towards the fish directly once */
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
			setAngularVelocity(getVelocity(), vangle);
		}
	}

	private boolean willPropagate() {
		if (width > matureWidth * 0.80 && height > matureHeight * 0.80) {
			Iterator<Fish> i = pond.getIterator();
			Fish f;
			while (i.hasNext()) {
				f = i.next();
				if (isCollidedBy(f)) {
					if (Math.random() < 0.001)
						return true;
				}
			}
		}
		return false;
	}
}
