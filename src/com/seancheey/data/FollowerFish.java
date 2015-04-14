package com.seancheey.data;

import java.util.ArrayList;
import java.util.Iterator;

public class FollowerFish extends GroupingFish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GuideFish guider;//the guider fish that the fish is going to follow

	public FollowerFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	@Override
	protected void perform() {
		super.perform();
		// if not following, choose a guide fish
		if (guider == null) {
			Iterator<Fish> i = getPond().getIterator();
			ArrayList<GuideFish> candidates = new ArrayList<GuideFish>(0);
			while (i.hasNext()) {
				Fish x = i.next();
				if (x instanceof GuideFish)
					candidates.add((GuideFish) x);
			}
			guider = candidates.get((int) (Math.random() * candidates.size()));
		}
		if (guider == null) {
			// change v randomly
			vx += Math.random() - 0.5;
			vy += Math.random() - 0.5;
		} else {
			// change v to approach the guider
			double v = Math.pow(vx * vx + vy * vy, 0.5), direction = Math
					.atan2((guider.getY() - y), (guider.getX() - x));
			double randV = Math.pow(guider.vx * guider.vx + guider.vy
					* guider.vy, 0.5);
			v = (randV + v) / 2;
			vx = v * Math.cos(direction);
			vy = v * Math.sin(direction);
		}
	}

}
