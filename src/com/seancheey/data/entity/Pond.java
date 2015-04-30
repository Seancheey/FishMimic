package com.seancheey.data.entity;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Fish;
import com.seancheey.interfaces.Container;
import com.seancheey.interfaces.Performable;
import com.seancheey.source.BackgroundPond;

/** the container to contain fish */
public class Pond extends Entity implements Container<Entity>, Performable {
	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

	private static final long serialVersionUID = 2L;
	/** fish container */
	private final ArrayList<Entity> fishes;
	/** the list of fish waited to be added */
	private ArrayList<Entity> added = new ArrayList<Entity>();
	/** the list of fish waited to be removed */
	private ArrayList<Entity> removed = new ArrayList<Entity>();

	public Pond(double width, double height, double x, double y, Image image,
			Container<Entity> container) {
		this(width, height, x, y, image, container, new ArrayList<Entity>());
	}

	/** original constructor */
	public Pond(double width, double height, double x, double y, Image image,
			Container<Entity> container, ArrayList<Entity> fishes) {
		super(width, height, x, y, image, container);
		this.fishes = fishes;
	}

	/** add the fish to the waiting list */
	@Override
	public void add(Entity fish) {
		added.add(fish);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pond other = (Pond) obj;
		if (fishes == null) {
			if (other.fishes != null)
				return false;
		} else if (!fishes.equals(other.fishes))
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	@Override
	public void fetchLostImage() {
		setImage(BackgroundPond.SEA);
	}

	private void flushWaitingFishList() {
		// add and remove the waiting fish
		fishes.addAll(added);
		for (Entity fish : removed) {
			fishes.remove(fish);
		}
		// clear the wait list
		added.clear();
		removed.clear();
	}

	/** return the first fish at the place */
	public Entity getFishAt(int x, int y) {
		for (Entity fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < fish.getWidth() / 2
					&& Math.abs(y - fish.getYCenter()) < fish.getHeight() / 2)
				return fish;
		}
		return null;
	}

	/** return a random fish exist in the fish array */
	public Entity getRandomFish() {
		return fishes.get((int) (Math.random() * fishes.size()));
	}

	@Override
	public Iterator<Entity> iterator() {
		return fishes.iterator();
	}

	/** method to prevent the fish from swimming outside */
	private void keepInside(Entity entity) {
		if (entity instanceof Fish) {
			Fish fish = (Fish) entity;
			if (fish.getX() < 0) {
				fish.setVx(-fish.getVx());
				fish.setX(0);
			}
			if (fish.getY() < 0) {
				fish.setVy(-fish.getVy());
				fish.setY(0);
			}
			if (fish.getX() + fish.getWidth() > width) {
				fish.setVx(-fish.getVx());
				fish.setX(width - fish.getWidth());
			}
			if (fish.getY() > height) {
				fish.setVy(-fish.getVy());
				fish.setY(height - fish.getHeight());
			}
		}
	}

	/** return the next fish of the argument in the array */
	public Entity nextFish(Entity fish) {
		int index = fishes.indexOf(fish);
		if (index + 1 == fishes.size()) {
			return fishes.get(0);
		} else {
			return fishes.get(index + 1);
		}
	}

	/** invoke next performance */
	@Override
	public void performNext() {
		flushWaitingFishList();
		// invoke next performance
		for (Entity fish : this) {
			keepInside(fish);
			fish.performNext();
		}
	}

	/** return random x form 0 to width */
	public int randX() {
		return (int) (Math.random() * getWidth());
	}

	/** return random y form 0 to height */
	public int randY() {
		return (int) (Math.random() * getHeight());
	}

	/** add the fish to the removal list */
	@Override
	public void remove(Entity fish) {
		removed.add(fish);
	}

	/** reset all fish's velocity and position */
	public void resetAll() {
		for (Entity fish : fishes) {
			fish.setX(randX());
			fish.setY(randY());
			if (fish instanceof Fish) {
				((Fish) fish).setVx(randV(5));
				((Fish) fish).setVy(randV(5));
			}
		}
	}

	/** resize the width and height of the pond */
	public synchronized void resize(Dimension dimension) {
		width = dimension.width;
		height = dimension.height;
	}
}
