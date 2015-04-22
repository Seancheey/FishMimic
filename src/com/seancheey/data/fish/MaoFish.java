package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class MaoFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Image picture = Toolkit.getDefaultToolkit().getImage(
			"res/github.png");
	// the standing and jump time left for the cat
	private int waitTime = 0, jumpTime = 0;

	public MaoFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super(width, height, x, y, vx, vy, pond, color);
	}

	public MaoFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void perform() {
		// set new action after finish all action
		if (waitTime <= 0 && jumpTime <= 0) {
			if (Math.random() < 0.5) {
				waitTime = (int) (Math.random() * 30);
			} else {
				jumpTime = (int) (Math.random() * 30);
				vx += (Math.random() * 2 - 1) * 2;
				vx = (Math.random() * 2 - 1) * 2;
			}
		}
		if (waitTime > 0) {
			waitTime -= 1;
		} else {
			super.perform();
			jumpTime -= 1;
		}
	}

	@Override
	protected void drawShape(Graphics g) {
		g.drawImage(picture, -width / 2, -height / 2, width, height, null);
	}

}
