package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class RoundFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	public RoundFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super(width, height, x, y, vx, vy, pond, color);
	}

	@Override
	protected void drawShape(Graphics g) {
		g.fillOval(-width / 2, -height / 2, width, height);
	}

	@Override
	protected void perform() {
		super.perform();
		// pick the next fish
		Fish randFish;
		int i;
		for (i = 0; i < getPond().getFishes().size(); i++) {
			if (getPond().getFishes().get(i).equals(this))
				break;
		}
		randFish = getPond().getFishes().get(
				i + 1 >= getPond().getFishes().size() ? 0 : i + 1);
		// close to it
		double v = Math.pow(vx * vx + vy * vy, 0.5), direction = Math.atan2(
				(randFish.getY() - y), (randFish.getX() - x));
		double randV = Math.pow(
				randFish.getVx() * randFish.getVx() + randFish.getVy()
						* randFish.getVy(), 0.5);
		v = (randV + v) / 2;
		vx = v * Math.cos(direction);
		vy = v * Math.sin(direction);

	}
}
