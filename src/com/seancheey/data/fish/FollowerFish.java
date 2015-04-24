package com.seancheey.data.fish;

import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;

public class FollowerFish extends GroupingFish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GuideFish guider;// the guider fish that the fish is going to follow

	public FollowerFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond);
	}

	/**
	 * @return the guider
	 */
	public GuideFish getGuider() {
		return guider;
	}

	/**
	 * @param guider
	 *            the guider to set
	 */
	public void setGuider(GuideFish guider) {
		this.guider = guider;
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
			if (candidates.size() > 0)
				guider = candidates.get((int) (Math.random() * candidates
						.size()));
		}
		if (guider == null) {
			// change v randomly
			vx += Math.random() - 0.5;
			vy += Math.random() - 0.5;
		} else {
			trackOnce(guider);
		}
	}

}
