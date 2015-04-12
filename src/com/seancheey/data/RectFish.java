package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RectFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g2.fillRect(xcenter, ycenter, width, height);
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
	}

	@Override
	protected void perform() {
		move();
		// pick a random fish
		Fish randFish = pond.getFishes().get(
				Math.abs((int) x % pond.getFishes().size()));
		// close to it
		vx += (randFish.getX() - x) / 1000;
		vy += (randFish.getY() - y) / 1000;
	}
}
