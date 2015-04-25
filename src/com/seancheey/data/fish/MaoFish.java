package com.seancheey.data.fish;

import java.awt.Image;

import com.seancheey.data.Fish;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class MaoFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeLeft = 20;
	private Fish aim;

	public MaoFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond.get("fish - cat"));
	}

	@Override
	protected void perform() {
		super.perform();
		// set new action after finish all action
		if (timeLeft <= 0 || aim == null) {
			timeLeft = (int) (Math.random() * 30);
			aim = getPond().getFishes().get(
					(int) (Math.random() * getPond().getFishes().size()));
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
