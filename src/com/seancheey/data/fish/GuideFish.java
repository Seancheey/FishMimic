package com.seancheey.data.fish;

import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;

public class GuideFish extends GroupingFish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeLeft = (int) (Math.random() * 20);
	private int aimX = FishGenerator.randX(), aimY = FishGenerator.randY();

	public GuideFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void perform() {
		super.perform();
		if (timeLeft < 0) {
			aimX = FishGenerator.randX();
			aimY = FishGenerator.randY();
			timeLeft = (int) (Math.random() * 20);
		}
		trackOnce(aimX, aimY);
		timeLeft--;
	}
}
