package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GroupingFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int edgeX = width / 4, edgeY = height / 4;

	public GroupingFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		int xcenter, ycenter;
		xcenter = (int) (x - width / 2);
		ycenter = (int) (y - height / 2);
		g2.rotate(Math.atan2(vy, vx), xcenter, ycenter);
		g2.fillRoundRect(xcenter, ycenter, width, height, edgeX, edgeY);
		g2.rotate(-Math.atan2(vy, vx), xcenter, ycenter);
	}

}
