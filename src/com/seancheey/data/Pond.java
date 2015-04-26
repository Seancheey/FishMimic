package com.seancheey.data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.data.entity.Fish;

public class Pond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int width, height;// width and height of the pond
	private final ArrayList<Fish> fishes;// fish container
	private Player player;
	// the list of fish waited to be added or removed
	private ArrayList<Fish> added = new ArrayList<Fish>(),
			removed = new ArrayList<Fish>();

	// List of constructor
	public Pond(int width, int height, ArrayList<Fish> fishes, Player player) {
		super();
		this.width = width;
		this.height = height;
		this.fishes = fishes;
		this.player = player;
	}

	public Pond(int width, int height) {
		this(width, height, new ArrayList<Fish>(), new Player(100));
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

	// list of getter
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	public Fish getFishAt(int x, int y) {
		for (Fish fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < fish.getWidth() / 2
					&& Math.abs(y - fish.getYCenter()) < fish.getHeight() / 2)
				return fish;
		}
		return null;
	}

	public Fish getRandomFish() {
		return fishes.get((int) (Math.random() * fishes.size()));
	}

	// list of setter
	public synchronized void resize(Dimension dimension) {
		width = dimension.width;
		height = dimension.height;
	}

	public synchronized ArrayList<Fish> getFishes() {
		return fishes;
	}

	public synchronized Iterator<Fish> getIterator() {
		return fishes.iterator();
	}

	public void remove(Fish fish) {
		removed.add(fish);
	}

	public void add(Fish fish) {
		added.add(fish);
	}

	// paint self
	public synchronized void paint(Graphics g) {
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	public synchronized void nextMove() {
		// add and remove the waiting fish
		fishes.addAll(added);
		for (Fish fish : removed) {
			fishes.remove(fish);
		}
		// chear the wait list
		added.clear();
		removed.clear();
		// invoke next perform
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().perform();
		}
	}
}
