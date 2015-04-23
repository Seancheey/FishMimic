package com.seancheey.data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Pond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width, height;// width and height of the pond
	private final ArrayList<Fish> fishes;// fish container

	// List of constructor
	public Pond(int width, int height, ArrayList<Fish> fishes) {
		super();
		this.width = width;
		this.height = height;
		this.fishes = fishes;
	}

	public Pond(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		fishes = new ArrayList<Fish>();
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

	public Fish getFishAt(int x, int y) {
		int allowDiff = 20;
		for (Fish fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < allowDiff
					&& Math.abs(y - fish.getYCenter()) < allowDiff)
				return fish;
		}
		return null;
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

	// paint self
	public synchronized void paint(Graphics g) {
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	// invoke next perform
	public synchronized void nextMove() {
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().perform();
		}
	}
}
