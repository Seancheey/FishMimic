package com.seancheey.data.entity.fish;

import java.awt.Image;

import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;
import com.seancheey.data.entity.Fish;

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
	public void perform() {
		super.perform();
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
	protected Image fetchLostImage() {
		return ImagePond.get("fish - bluefish");
	}
}
