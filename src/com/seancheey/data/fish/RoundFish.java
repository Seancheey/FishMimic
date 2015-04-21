package com.seancheey.data.fish;

import java.awt.Graphics;
import java.awt.Graphics2D;

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

	@Override
	protected void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g2.fillOval(xcenter, ycenter, width, height);
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
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
