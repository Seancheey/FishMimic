package com.seancheey.data.entity.fish;

import com.seancheey.data.ImagePond;
import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.Pond;

public class RoundFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundFish(double width, double height, double x, double y,
			double vx, double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - bluefish"));
	}

	@Override
	public void performNext() {
		super.performNext();
		// pick the next fish
		Fish nextFish = getPond().nextFish(this);
		// close to it
		trackOnce(nextFish);
	}

	@Override
	public Fish clone() {
		return new RoundFish(width, height, x, y, vx, vy, pond);
	}

	@Override
	public void fetchLostImage() {
		image = ImagePond.get("fish - bluefish");
	}
}
