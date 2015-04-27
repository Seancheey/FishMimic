package com.seancheey.data.entity.fish;

import com.seancheey.data.ImagePond;
import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.Pond;

public class RainbowFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Fish aim;

	public RainbowFish(double width, double height, double x, double y,
			double vx, double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - brownfish1"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#perform()
	 */
	@Override
	public void performNext() {
		super.performNext();
		// search for other fishes
		if (aim == null) {
			aim = getPond().getRandomFish();
		}
		if (isCollidedBy(aim)) {
			// collision becomes meaningless
			aim = null;
		} else
			trackOnce(aim);
	}

	@Override
	public Fish clone() {
		return new RainbowFish(width, height, x, y, vx, vy, pond);
	}

	@Override
	public void fetchLostImage() {
		image = ImagePond.get("fish - cat");
	}
}
