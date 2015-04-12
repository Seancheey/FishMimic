package com.seancheey;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.seancheey.data.Fish;
import com.seancheey.data.Pond;
import com.seancheey.data.RectFish;
import com.seancheey.data.RoundFish;
import com.seancheey.gui.ControlFrame;
import com.seancheey.gui.PondPanel;

public class Main {
	public static final String NAME = "Moving fishes";
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width,
			HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static int DELAY;

	public static void main(String[] args) {
		// initiate the pond
		Pond p = new Pond(WIDTH, HEIGHT);
		// generate some fish
		for (int i = 0; i < 300; i++)
			p.getFishes().add(FishGenerator.generate(p));
		// add pond to a panel
		PondPanel pondP = new PondPanel(p);
		// create a new JFrame and add the panel
		JFrame f = new ControlFrame();
		f.setVisible(true);
		f.getContentPane().add(pondP);
	}

	// Library Functions
	public static Color randColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return new Color(r, g, b);
	}
}

class FishGenerator {
	public static final Fish generate(Pond p) {
		double seed = Math.random();
		if (seed < 0.1)
			return new RectFish(30, 10, randWidth(), randHeight(), randV(5),
					randV(5), p);
		else
			return new RoundFish(20, 10, randWidth(), randHeight(), randV(5),
					randV(5), p);
	}

	private static final int randWidth() {
		return (int) (Math.random() * Main.WIDTH);
	}

	private static final int randHeight() {
		return (int) (Math.random() * Main.HEIGHT);
	}

	private static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

}