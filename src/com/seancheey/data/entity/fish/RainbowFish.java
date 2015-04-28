package com.seancheey.data.entity.fish;

import com.seancheey.Container;
import com.seancheey.data.Entity;
import com.seancheey.data.ImagePond;
import com.seancheey.data.entity.Fish;

public class RainbowFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RainbowFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, ImagePond.get("fish - brownfish1"),
				container, vx, vy);
	}

	public RainbowFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy,
			double matureWidth, double matureHeight) {
		super(width, height, x, y, ImagePond.get("fish - brownfish1"),
				container, vx, vy, matureWidth, matureHeight);
	}

	@Override
	public Fish clone() {
		return new RainbowFish(width, height, x, y, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public void fetchLostImage() {
		image = ImagePond.get("fish - cat");
	}

	@Override
	public void performNext() {
		super.performNext();
		// TODO moving method
	}
}
