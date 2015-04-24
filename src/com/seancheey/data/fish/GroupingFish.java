package com.seancheey.data.fish;

import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public abstract class GroupingFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int edgeX = width / 4, edgeY = height / 4;// the size of the edge of
														// the round rectangle
														// painted

	// Constructor
	public GroupingFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seancheey.data.Fish#drawShape(java.awt.Graphics)
	 */
	@Override
	protected void drawShape(Graphics g) {
		g.fillRoundRect(-width / 2, -height / 2, width, height, edgeX, edgeY);
	}

}
