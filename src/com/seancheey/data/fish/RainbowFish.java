package com.seancheey.data.fish;

import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class RainbowFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Fish aim;

	public RainbowFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - brownfish1"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#perform()
	 */
	@Override
	protected void perform() {
		super.perform();
		// search for other fishes
		if (aim == null) {
			aim = getPond().getFishes().get(
					(int) (getPond().getFishes().size() * Math.random()));
		}
		trackOnce(aim);
		// change color if close enough
		if (isCollidedBy(aim)) {
			// collision becomes meaningless
			aim = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#paint(java.awt.Graphics)
	 */
	@Override
	protected void drawShape(Graphics g) {
		g.drawImage(image, -width / 2, -height / 2, width, height, null);
	}

	@Override
	protected Fish clone() {
		return new RainbowFish(width,height,x,y,vx,vy,pond);
	}
}
