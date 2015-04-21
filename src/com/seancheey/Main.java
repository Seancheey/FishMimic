package com.seancheey;

import java.awt.Toolkit;

import com.seancheey.gui.Menu;

public class Main {
	public static final String NAME = "Moving fishes";
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width,
			HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static Gui controlFrame;

	public static void main(String[] args) {
		controlFrame = new Gui();
		controlFrame.add(new Menu());
		controlFrame.setVisible(true);
	}
}