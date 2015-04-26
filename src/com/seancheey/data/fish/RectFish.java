package com.seancheey.data.fish;

import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class RectFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RectFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - redfish"));
	}

	@Override
	protected void drawShape(Graphics g) {
		g.drawImage(image, -width / 2, -height / 2, width, height, null);
	}

	@Override
	protected void perform() {
		super.perform();
		// pick a random fish
		Fish randFish = pond.getFishes().get(
				Math.abs((int) x % pond.getFishes().size()));
		// close to it
		if (immobilized == false) {
			vx += (randFish.getX() - x) / 1000;
			vy += (randFish.getY() - y) / 1000;
		} else {
			trackOnce(randFish);
		}
	}

	@Override
	protected Fish clone() {
		return new RectFish(width,height,x,y,vx,vy,pond);
	}

}
