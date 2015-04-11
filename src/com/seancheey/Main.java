package com.seancheey;


import javax.swing.JFrame;

import com.seancheey.data.Pond;
import com.seancheey.data.RectFish;
import com.seancheey.gui.ControlFrame;
import com.seancheey.gui.PondPanel;

public class Main {
	public static final String NAME = "Moving fishes";
	public static final int WIDTH = 500, HEIGHT = 500;

	public static void main(String[] args) {
		// init the pond
		Pond p = new Pond(WIDTH, HEIGHT);
		for (int i = 0; i < 20; i++)
			p.getFishes().add(
					new RectFish(20, 10, 250, 250, Math.random() * 5 - 2.5,
							Math.random() * 5 - 2.5, p));
		// add it to a pond panel
		PondPanel pondP = new PondPanel(p);
		// create a new JFrame and add the panel
		JFrame f = new ControlFrame();
		f.setVisible(true);
		f.getContentPane().add(pondP);
	}
}
