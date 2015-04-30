package com.seancheey.interfaces;

import java.awt.Dimension;

import javax.swing.JPanel;

public interface GUIContainer {
	void switchPanel(JPanel oldPanel, JPanel newPanel);

	Dimension getSize();

	int getWidth();

	int getHeight();
}
