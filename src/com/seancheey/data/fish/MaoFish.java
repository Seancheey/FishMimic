package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.seancheey.data.Fish;
import com.seancheey.data.FishGenerator;
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
				waitTime = (int) (Math.random() * 10);
			} else {
				jumpTime = (int) (Math.random() * 10);
				vx = FishGenerator.randV(5);
				vx = FishGenerator.randV(5);
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
	protected void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g.drawImage(picture, xcenter, ycenter, width, height, null);
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
	}

}
