package com.seancheey.data.fish;

import com.seancheey.data.Pond;

public class GuideFish extends GroupingFish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuideFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void perform() {
		super.perform();
		// change v randomly
		if (Math.random() < 0.005) {
			vx *= Math.random() * 2 - 1;
		}
		if (Math.random() < 0.005) {
			vy *= Math.random() * 2 - 1;
		}
		vx += Math.random() - 0.5;
		vy += Math.random() - 0.5;
	}
}