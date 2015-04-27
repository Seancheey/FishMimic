package com.seancheey.data.entity.fish;

import com.seancheey.data.ImagePond;
import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.Pond;

public class MaoFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeLeft = 20;
	private Fish aim;

	public MaoFish(double width, double height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond.get("fish - cat"));
	}

	@Override
	public void performNext() {
		super.performNext();
		// set new action after finish all action
		if (timeLeft <= 0 || aim == null) {
			timeLeft = (int) (Math.random() * 30);
			aim = getPond().getRandomFish();
		}
		trackOnce(aim);
		timeLeft--;
	}

	@Override
	public Fish clone() {
		return new MaoFish(width, height, x, y, vx, vy, pond);
	}

	@Override
	public void fetchLostImage() {
		image = ImagePond.get("fish - cat");
	}

}
