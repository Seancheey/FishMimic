package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.seancheey.Main;

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
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g2.fillRect(xcenter, ycenter, width, height);
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
	}

	@Override
	protected void move() {
		// Touch the wall to reflect
		if (x > getPond().getWidth() || x < 0) {
			vx *= -0.92;
		}
		if (y > getPond().getHeight() || y < 0) {
			vy *= -0.92;
		}
		// to prevent the ball from sticking into wall
		if (x > getPond().getWidth()) {
			x = Main.WIDTH - width;
		}
		if (y > getPond().getHeight()) {
			y = Main.HEIGHT - height;
		}
		if (x < 0) {
			x = width;
		}
		if (y < 0) {
			y = height;
		}
		// have a move
		x += vx;
		y += vy;
		// pick a random fish
		Fish randFish = pond.getFishes().get(
				Math.abs((int) x % pond.getFishes().size()));
		// close to it
		vx += (randFish.getX() - x) / 1000;
		vy += (randFish.getY() - y) / 1000;
	}
}
