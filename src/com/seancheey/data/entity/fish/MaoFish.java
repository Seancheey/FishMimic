package com.seancheey.data.entity.fish;

import java.awt.Image;

import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;
import com.seancheey.data.entity.Fish;

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
	public void perform() {
		super.perform();
		// set new action after finish all action
		if (timeLeft <= 0 || aim == null) {
			timeLeft = (int) (Math.random() * 30);
			aim = getPond().getRandomFish();
		}
		trackOnce(aim);
		timeLeft--;
	}

	@Override
	protected Fish clone() {
		return new MaoFish(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected Image fetchLostImage() {
		return ImagePond.get("fish - cat");
	}

}
