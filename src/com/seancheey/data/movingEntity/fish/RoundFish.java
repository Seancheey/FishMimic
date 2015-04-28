package com.seancheey.data.movingEntity.fish;

import java.awt.Image;

import com.seancheey.Container;
import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Fish;
import com.seancheey.source.FishPond;

public class RoundFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundFish(double width, double height, double x, double y,
			Image image, Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, FishPond.BLUE, container, vx, vy);
	}

	public RoundFish(double width, double height, double x, double y,
			Image image, Container<Entity> container, double vx, double vy,
			double matureWidth, double matureHeight) {
		super(width, height, x, y, FishPond.BLUE, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public Fish clone() {
		return new RoundFish(width, height, x, y, image, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public void fetchLostImage() {
		image = FishPond.BLUE;
	}

	@Override
	public void performNext() {
		super.performNext();
		// TODO moving method
	}
}
