package com.seancheey.data.movingEntity;

import java.awt.Image;

import com.seancheey.data.Entity;
import com.seancheey.data.entity.MovingEntity;
import com.seancheey.data.entity.Pond;
import com.seancheey.interfaces.Container;

public abstract class Fish extends MovingEntity {
	private static final long serialVersionUID = 2L;
	/** if the fish is fixed */
	protected boolean immobilized = false;
	/** the energy used by the fish(used to calculate shear) */
	private double energyUsed;
	/** the size of mature fish */
	protected final double matureWidth, matureHeight;

	/**`l*/
	public Fish(double width, double height, double x, double y, Image image,
			Container<Entity> container, double vx, double vy) {
		this(width, height, x, y, image, container, vx, vy, 150, 75);
	}

	public Fish(double width, double height, double x, double y, Image image,
			Container<Entity> container, double vx, double vy,
			double matureWidth, double matureHeight) {
		super(width, height, x, y, image, container, vx, vy);
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

	/** return the price of selling the fish */
	public int getPrice() {
		return (int) ((width + height) / 2);
	}

	/** grow a bit or die if mature */
	private void grow() {
		if (width <= matureWidth || height <= matureHeight) {
			width += Math.random() / 10;
			height += Math.random() / 20;
		} else {
			if (Math.random() < 0.01) {
				getContainer().remove(this);
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

	/** perform the next movement */
	public void performNext() {
		// have a move
		if (!immobilized) {
			super.performNext();
		}
		grow();
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
		getContainer().add(fish);
	}

	public void reset(double width, double height, double x, double y,
			double vx, double vy) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public void setAngularVelocity(double velocity, double angle) {
		vx = Math.cos(angle) * velocity;
		vy = Math.sin(angle) * velocity;
	}

	public void setFixed(boolean value) {
		immobilized = value;
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
			for (Entity f : getContainer()) {
				if (isCollidedBy((Fish) f)) {
					if (Math.random() < 0.001)
						return true;
				}
			}
		}
		return false;
	}
}
