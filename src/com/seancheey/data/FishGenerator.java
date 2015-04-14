package com.seancheey.data;

import java.awt.Color;
import java.io.Serializable;

import com.seancheey.Main;

public class FishGenerator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Fish generate(Pond p) {
		double seed = Math.random();
		if (seed < 0.0)
			return new RectFish(20, 10, randX(), randY(), randV(5),
					randV(5), p);
		else
			return new RoundFish(20, 10, randX(), randY(), randV(5),
					randV(5), p);
	}

	public static final Fish generate(String type, int width, int height,
			double x, double y, double vx, double vy, Pond pond, Color color) {
		switch (type) {
		case "RectFish":
			return new RectFish(width, height, x, y, vx, vy, pond);
		case "RoundFish":
			return new RoundFish(width, height, x, y, vx, vy, pond);
		case "FollowerFish":
			return new FollowerFish(width, height, x, y, vx, vy, pond);
		case "GuideFish":
			return new GuideFish(width, height, x, y, vx, vy, pond);
		default:
			throw new IllegalArgumentException("Fish type mismatch!");
		}
	}

	public static final int randX() {
		return (int) (Math.random() * Main.WIDTH);
	}

	public static final int randY() {
		return (int) (Math.random() * Main.HEIGHT);
	}

	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

	public static final Color randColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return new Color(r, g, b);
	}
}