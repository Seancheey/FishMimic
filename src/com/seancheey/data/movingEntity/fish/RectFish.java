package com.seancheey.data.movingEntity.fish;

import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Fish;
import com.seancheey.interfaces.Container;
import com.seancheey.source.FishPond;

public class RectFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RectFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, FishPond.RED, container, vx, vy);
	}

	public RectFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy,
			double matureWidth, double matureHeight) {
		super(width, height, x, y, FishPond.RED, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public Fish clone() {
		return new RectFish(width, height, x, y, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public void fetchLostImage() {
		image = FishPond.RED;
	}

	@Override
	public void performNext() {
		super.performNext();
		// TODO moving method
	}

}
