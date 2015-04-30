package com.seancheey;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.seancheey.interfaces.GUIContainer;
import com.seancheey.source.BackgroundPond;

public class Gui extends JFrame implements GUIContainer {
	private static final long serialVersionUID = 1L;

	public Gui() {
		super(Main.NAME);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconImage(BackgroundPond.SEA);
		setMinimumSize(new Dimension(500, 300));
		setUndecorated(false);
	}

	@Override
	public void switchPanel(JPanel oldPanel, JPanel newPanel) {
		getContentPane().remove(oldPanel);
		getContentPane().add(newPanel);
		setVisible(true);
	}
}
