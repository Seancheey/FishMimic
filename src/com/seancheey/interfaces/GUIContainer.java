package com.seancheey.interfaces;

import java.awt.Dimension;

import javax.swing.JPanel;

public interface GUIContainer {
	int getHeight();

	Dimension getSize();

	int getWidth();

	void switchPanel(JPanel oldPanel, JPanel newPanel);
}
