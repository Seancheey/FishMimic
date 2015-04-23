package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.seancheey.data.Fish;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class MaoFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Image picture = ImagePond.get("fish - cat");
	private int timeLeft = 20;
	private Fish aim;

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
		super.perform();
		// set new action after finish all action
		if (timeLeft <= 0 || aim == null) {
			timeLeft = (int) (Math.random() * 30);
			aim = getPond().getFishes().get(
					(int) (Math.random() * getPond().getFishes().size()));
		}
		trackOnce(aim);
		timeLeft--;
	}

	@Override
	protected void drawShape(Graphics g) {
		g.drawImage(picture, -width / 2, -height / 2, width, height, null);
	}
}
