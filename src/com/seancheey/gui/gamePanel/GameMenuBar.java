package com.seancheey.gui.gamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Pond;
import com.seancheey.gui.CreditPanel;
import com.seancheey.gui.FishSelectPanel;
import com.seancheey.gui.GamePanel;
import com.seancheey.gui.Menu;

public class GameMenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JMenu> menus = new ArrayList<JMenu>();
	private ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 22);
	private Pond pond;
	private GamePanel gamePanel;

	public GameMenuBar(GamePanel gamePanel, Pond pond) {
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
		items.add(new JMenuItem("Save process..."));
		items.add(new JMenuItem("Read process..."));
		items.add(new JMenuItem("Exit..."));

		items.add(new JMenuItem("Add new fish..."));
		items.add(new JMenuItem("Reset all fish"));
		items.add(new JMenuItem("Sell all fish"));

		items.add(new JMenuItem("About..."));

		for (JMenuItem item : items) {
			item.addActionListener(this);
			item.setFont(UNIFIED_FONT);
		}
		// add items to menus
		menus.get(0).add(items.get(0));
		menus.get(0).add(items.get(1));
		menus.get(0).add(items.get(2));

		menus.get(1).add(items.get(3));
		menus.get(1).add(items.get(4));
		menus.get(1).add(items.get(5));

		menus.get(2).add(items.get(6));

		// add menus to the bar
		for (JMenu menu : menus) {
			add(menu);
		}
	}

	private void switchOutTo(JPanel panel) {
		// disable the menu bar
		Main.controlFrame.setJMenuBar(null);
		// stop refreshing fish's movement
		gamePanel.getPondPanel().stopRefreshing();
		// switch the panel
		Main.controlFrame.switchPanel(gamePanel, panel);
	}

	private void popSaveDialog() {
		JFileChooser fileChooser = new JFileChooser("dat");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showSaveDialog(gamePanel);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(false);
		File file = fileChooser.getSelectedFile();
		if (file != null) {
			try {
				FileOutputStream fileout = new FileOutputStream(file);
				ObjectOutputStream objout = new ObjectOutputStream(fileout);
				objout.writeObject(pond);
				fileout.close();
				objout.close();
			} catch (Exception e) {
			}
		}
	}

	private void popReadDialog() {
		JFileChooser fileChooser = new JFileChooser("dat");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showOpenDialog(gamePanel);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(false);
		File file = fileChooser.getSelectedFile();
		if (file != null) {
			try {
				FileInputStream filein = new FileInputStream(file);
				ObjectInputStream objin = new ObjectInputStream(filein);
				Object p = objin.readObject();
				if (p instanceof Pond) {
					pond = (Pond) p;
					gamePanel.getPondPanel().setPond(pond);
				}
				filein.close();
				objin.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) a.getSource();
			switch (item.getText()) {
			case "Save process...":
				popSaveDialog();
				break;
			case "Read process...":
				popReadDialog();
				break;
			case "Exit...":
				switchOutTo(new Menu());
				break;
			case "Add new fish...":
				switchOutTo(new FishSelectPanel(pond) {

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
			case "Reset all fish":
				pond.resetAll();
				break;
			case "Sell all fish":
				pond.sellAll();
				break;
			case "About...":
				switchOutTo(new CreditPanel() {

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
