package com.seancheey.data;

import java.io.Serializable;

import com.seancheey.Main;
import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.fish.MaoFish;
import com.seancheey.data.entity.fish.RainbowFish;
import com.seancheey.data.entity.fish.RectFish;
import com.seancheey.data.entity.fish.RoundFish;

public class FishGenerator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;
	private Pond pond;

	public FishGenerator(Pond pond) {
		this.pond = pond;
	}

	public Fish generateRandom(int x, int y) {
		int fishTypeNum = 4;
		switch (new java.util.Random().nextInt(fishTypeNum)) {
		case 0:
			return new RectFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x, y, randV(5),
					randV(5), pond);
		case 1:
			return new RoundFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x, y, randV(5),
					randV(5), pond);
		case 2:
			return new RainbowFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x, y,
					randV(5), randV(5), pond);
		case 3:
			return new MaoFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x, y, randV(5),
					randV(5), pond);
		}
		return null;
	}

	public Fish generateRandom() {
		return generateRandom(randX(), randY());
	}

	public Fish generate(String type, int width, int height, double x,
			double y, double vx, double vy) {
		switch (type) {
		case "RectFish":
			return new RectFish(width, height, x, y, vx, vy, pond);
		case "RoundFish":
			return new RoundFish(width, height, x, y, vx, vy, pond);
		case "MaoFish":
			return new MaoFish(width, height, x, y, vx, vy, pond);
		case "RainbowFish":
			return new RainbowFish(width, height, x, y, vx, vy, pond);
		default:
			throw new IllegalArgumentException("Fish type mismatch! :" + type);
		}
	}

	public static final int randX() {
		return (int) (Math.random() * Main.controlFrame.getWidth());
	}

	public static final int randY() {
		return (int) (Math.random() * Main.controlFrame.getHeight());
	}

	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}
}