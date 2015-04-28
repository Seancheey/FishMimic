package com.seancheey.data.movingEntity.fish;

import com.seancheey.Container;
import com.seancheey.FishPond;
import com.seancheey.data.Entity;
import com.seancheey.data.movingEntity.Fish;

public class MaoFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaoFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy) {
		super(width, height, x, y, FishPond.GITHUB, container, vx, vy);
	}

	public MaoFish(double width, double height, double x, double y,
			Container<Entity> container, double vx, double vy,
			double matureWidth, double matureHeight) {
		super(width, height, x, y, FishPond.GITHUB, container, vx, vy,
				matureWidth, matureHeight);
	}

	@Override
	public Fish clone() {
		return new MaoFish(width, height, x, y, container, vx, vy, matureWidth,
				matureHeight);
	}

	@Override
	public void fetchLostImage() {
		image = FishPond.GITHUB;
	}

	@Override
	public void performNext() {
		super.performNext();
		// TODO moving method
	}

}
