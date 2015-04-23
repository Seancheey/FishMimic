package com.seancheey.gui;

import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Pond;
import com.seancheey.gui.gamePanel.GameMenuBar;
import com.seancheey.gui.gamePanel.PondGraph;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PondGraph pondPanel = new PondGraph(null);

	public GamePanel(Pond pond) {
		// add menu bar
		Main.controlFrame.setJMenuBar(new GameMenuBar(this, pond));
		this.pondPanel = new PondGraph(pond);
		add(pondPanel);
	}
}
