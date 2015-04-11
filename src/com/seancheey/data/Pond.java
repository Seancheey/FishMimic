package com.seancheey.data;


import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Pond implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width, height;
	private final ArrayList<Fish> fishes;

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
		fishes = new ArrayList<Fish>(10);
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Fish> getFishes() {
		return fishes;
	}

	public Iterator<Fish> getIterator() {
		return fishes.iterator();
	}

	public void paint(Graphics g) {
		Iterator<Fish> i = fishes.iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	public void nextMove() {
		Iterator<Fish> i = fishes.iterator();
		while (i.hasNext()) {
			i.next().perform();
		}
	}
}
