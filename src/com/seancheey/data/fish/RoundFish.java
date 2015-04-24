package com.seancheey.data.fish;

import java.awt.Graphics;

import com.seancheey.data.Fish;
import com.seancheey.data.ImagePond;
import com.seancheey.data.Pond;

public class RoundFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundFish(int width, int height, double x, double y, double vx,
			double vy, Pond pond) {
		super(width, height, x, y, vx, vy, pond, ImagePond
				.get("fish - bluefish"));
	}

	@Override
	protected void drawShape(Graphics g) {
		g.drawImage(image, -width / 2, -height / 2, width, height, null);
	}

	@Override
	protected void perform() {
		super.perform();
		// pick the next fish
		Fish nextFish;
		int i = 0;
		for (Fish fish : getPond().getFishes()) {
			if (fish.equals(this))
				break;
			i++;
		}
		nextFish = getPond().getFishes().get(
				i + 1 >= getPond().getFishes().size() ? 0 : i + 1);
		// close to it
		trackOnce(nextFish);
	}
}
