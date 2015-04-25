package com.seancheey.gui.gamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Fish;
import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;
import com.seancheey.gui.CreditPanel;
import com.seancheey.gui.FishSelectPanel;
import com.seancheey.gui.GamePanel;

public class GameMenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JMenu> menus = new ArrayList<JMenu>();
	private ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 25);
	private Pond pond;
	private JPanel gamePanel;

	public GameMenuBar(JPanel gamePanel, Pond pond) {
		// initialize
		this.pond = pond;
		this.gamePanel = gamePanel;
		setBackground(Color.WHITE);
		// set the menus
		menus.add(new JMenu("File"));
		menus.add(new JMenu("Edit"));
		menus.add(new JMenu("Help"));
		for (JMenu menu : menus) {
			menu.setFont(UNIFIED_FONT);
			menu.setBackground(Color.WHITE);
		}
		// set the menu items
		items.add(new JMenuItem("Save process"));
		items.add(new JMenuItem("Read process"));

		items.add(new JMenuItem("Add new fish"));
		items.add(new JMenuItem("Reset all fishes"));
		items.add(new JMenuItem("Remove all fishes"));

		items.add(new JMenuItem("About"));

		for (JMenuItem item : items) {
			item.addActionListener(this);
			item.setFont(UNIFIED_FONT);
		}
		// add items to menus
		menus.get(0).add(items.get(0));
		menus.get(0).add(items.get(1));
		menus.get(1).add(items.get(2));
		menus.get(1).add(items.get(3));
		menus.get(1).add(items.get(4));
		menus.get(2).add(items.get(5));

		// add menus to the bar
		for (JMenu menu : menus) {
			add(menu);
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) a.getSource();
			switch (item.getText()) {
			case "Add new fish":
				// disable the menu bar
				Main.controlFrame.setJMenuBar(null);
				// switch the the fish selection panel
				Main.controlFrame.switchPanel(gamePanel, new FishSelectPanel(
						pond) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void addAction() {
						Main.controlFrame.switchPanel(this, new GamePanel(
								getPond()));

					}

					@Override
					public void backAction() {
						Main.controlFrame.switchPanel(this, new GamePanel(
								getPond()));
					}
				});
				break;
			case "Reset all fishes":
				for (Fish fish : pond.getFishes()) {
					fish.setX(FishGenerator.randX());
					fish.setY(FishGenerator.randY());
					fish.setVx(FishGenerator.randV(5));
					fish.setVy(FishGenerator.randV(5));
				}
				break;
			case "Remove all fishes":
				pond.getFishes().clear();
				break;
			case "About":
				Main.controlFrame.setJMenuBar(null);
				Main.controlFrame.switchPanel(gamePanel, new CreditPanel() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void backAction() {
						Main.controlFrame
								.switchPanel(this, new GamePanel(pond));
					}
				});

				break;
			default:
				System.out.println("Unknown menu item");
			}
		}
	}
}
