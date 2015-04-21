package com.seancheey.data.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class EaterFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EaterFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond, Color color) {
		super(width, height, x, y, vx, vy, pond, color);
	}

	public EaterFish(int width, int height, double x, double y, double vx,
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#paint(java.awt.Graphics)
	 */
	@Override
	protected void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g2.fillPolygon(new Polygon(new int[] { xcenter + 2 * width, xcenter,
				xcenter }, new int[] { ycenter, ycenter + height,
				ycenter - height }, 3));
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
	}

}
