package com.seancheey.data.entity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.Container;
import com.seancheey.Performable;
import com.seancheey.data.Entity;
import com.seancheey.data.ImagePond;
import com.seancheey.data.movingEntity.Fish;

public class Pond extends Entity implements Container<Fish>, Performable {
	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

	private static final long serialVersionUID = 2L;
	/** fish container */
	private final ArrayList<Fish> fishes;
	/** the list of fish waited to be added */
	private ArrayList<Fish> added = new ArrayList<Fish>();
	/** the list of fish waited to be removed */
	private ArrayList<Fish> removed = new ArrayList<Fish>();

	public Pond(double width, double height, double x, double y, Image image,
			Container<Entity> container) {
		this(width, height, x, y, image, container, new ArrayList<Fish>());
	}

	/** original constructor */
	public Pond(double width, double height, double x, double y, Image image,
			Container<Entity> container, ArrayList<Fish> fishes) {
		super(width, height, x, y, image, container);
		this.fishes = fishes;
	}

	/** add the fish to the waiting list */
	@Override
	public void add(Fish fish) {
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
		setImage(ImagePond.get("background - sea"));
	}

	private void flushWaitingFishList() {
		// add and remove the waiting fish
		fishes.addAll(added);
		for (Fish fish : removed) {
			fishes.remove(fish);
		}
		// clear the wait list
		added.clear();
		removed.clear();
	}

	/** return the first fish at the place */
	public Fish getFishAt(int x, int y) {
		for (Fish fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < fish.getWidth() / 2
					&& Math.abs(y - fish.getYCenter()) < fish.getHeight() / 2)
				return fish;
		}
		return null;
	}

	/** return a random fish exist in the fish array */
	public Fish getRandomFish() {
		return fishes.get((int) (Math.random() * fishes.size()));
	}

	@Override
	public Iterator<Fish> iterator() {
		return fishes.iterator();
	}

	/** return the next fish of the argument in the array */
	public Fish nextFish(Fish fish) {
		int index = fishes.indexOf(fish);
		if (index + 1 == fishes.size()) {
			return fishes.get(0);
		} else {
			return fishes.get(index + 1);
		}
	}

	/** paint the pond itself with all fish */
	@Override
	public synchronized void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, (int) width, (int) height, null);
		for (Fish fish : this) {
			fish.paint(g);
		}
	}

	/** invoke next performance */
	@Override
	public void performNext() {
		flushWaitingFishList();
		// invoke next performance
		for (Fish fish : this) {
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
	public void remove(Fish fish) {
		removed.add(fish);
	}

	/** reset all fish's velocity and position */
	public void resetAll() {
		for (Fish fish : fishes) {
			fish.setX(randX());
			fish.setY(randY());
			fish.setVx(randV(5));
			fish.setVy(randV(5));
		}
	}

	/** resize the width and height of the pond */
	public synchronized void resize(Dimension dimension) {
		width = dimension.width;
		height = dimension.height;
	}
}