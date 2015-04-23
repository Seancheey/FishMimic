package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class RainbowFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Fish aim;

	public RainbowFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super(width, height, x, y, vx, vy, pond, color);
	}

	public RainbowFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
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
			aim.setColor(new Color(
					(color.getRed() + aim.getColor().getRed()) / 2, (color
							.getGreen() + aim.getColor().getGreen()) / 2,
					(color.getBlue() + aim.getColor().getBlue()) / 2));
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
		g.fillPolygon(new Polygon(new int[] { width, 0, 0 }, new int[] { 0,
				height / 2, -height / 2 }, 3));
	}

}
