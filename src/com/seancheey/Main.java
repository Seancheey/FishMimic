package com.seancheey;

import com.seancheey.gui.Menu;

public class Main {
	public static final String NAME = "Moving fishes";
	public static Gui controlFrame;

	public static void main(String[] args) {
		controlFrame = new Gui();
		controlFrame.add(new Menu());
		controlFrame.setVisible(true);
	}
}