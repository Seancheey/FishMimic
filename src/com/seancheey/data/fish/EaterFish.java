package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class EaterFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EaterFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super(width, height, x, y, vx, vy, pond, color);
	}

	public EaterFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#perform()
	 */
	@Override
	protected void perform() {
		super.perform();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#paint(java.awt.Graphics)
	 */
	@Override
	protected void paint(Graphics g) {
		super.paint(g);
		g.fillArc((int) x, (int) y, width, height,
				(int) Math.toDegrees(Math.atan2(vy, vx)) + 90, 45);
	}

}
