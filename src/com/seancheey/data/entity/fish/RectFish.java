package com.seancheey.data.entity.fish;

import java.awt.Image;

import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;
import com.seancheey.data.entity.Fish;

public class RectFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RectFish(double width, double height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - redfish"));
	}

	@Override
	public void perform() {
		super.perform();
		// pick a random fish
		Fish randFish = pond.getRandomFish();
		// close to it
		if (immobilized == false) {
			vx += (randFish.getX() - x) / 1000;
			vy += (randFish.getY() - y) / 1000;
		} else {
			trackOnce(randFish);
		}
	}

	@Override
	public Fish clone() {
		return new RectFish(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected Image fetchLostImage() {
		return ImagePond.get("fish - redfish");
	}

}
