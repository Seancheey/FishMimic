package com.seancheey.gui;

import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Pond;
import com.seancheey.gui.gamePanel.GameMenuBar;
import com.seancheey.gui.gamePanel.PondPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PondPanel pondPanel = new PondPanel(null);

	public GamePanel(Pond pond) {
		if (pond == null)
			pond = new Pond(getWidth(), getHeight());
		// add menu bar
		Main.controlFrame.setJMenuBar(new GameMenuBar(this, pond));
		this.pondPanel = new PondPanel(pond);
		add(pondPanel);
	}

	/**
	 * @return the pondPanel
	 */
	public PondPanel getPondPanel() {
		return pondPanel;
	}
	
}
