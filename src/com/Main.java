package com;

import com.seancheey.Gui;
import com.seancheey.gui.Menu;

public class Main {
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.add(new Menu(gui));
		gui.setVisible(true);
	}

	public static final String NAME = "Moving fishes";
}