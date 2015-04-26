package com.seancheey.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Pond;
import com.seancheey.gui.gamePanel.FishBukketPanel;
import com.seancheey.gui.gamePanel.FishMouseListener;
import com.seancheey.gui.gamePanel.GameMenuBar;
import com.seancheey.gui.gamePanel.PondPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PondPanel pondPanel;
	private FishBukketPanel fishBukket;
	private FishMouseListener fishMouseListener;

	public GamePanel(Pond pond) {
		// initialize
		if (pond == null)
			pond = new Pond(getWidth(), getHeight());

		pondPanel = new PondPanel(pond);
		fishBukket = new FishBukketPanel();
		fishMouseListener = new FishMouseListener(pondPanel);
		// add listeners
		pondPanel.addMouseListener(fishMouseListener);
		pondPanel.addMouseMotionListener(fishMouseListener);
		// add menu bar
		Main.controlFrame.setJMenuBar(new GameMenuBar(this, pond));
		add(pondPanel);
		add(fishBukket);
		setGridLayout();
	}

	private void setGridLayout() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 9;
		c.gridheight = 1;
		layout.setConstraints(pondPanel, c);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 1;
		c.gridx = 10;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		layout.setConstraints(fishBukket, c);
		setLayout(layout);
	}

	public PondPanel getPondPanel() {
		return pondPanel;
	}
}
