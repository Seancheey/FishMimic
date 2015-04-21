package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.seancheey.Main;
import com.seancheey.data.Fish;
import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;

public class PondPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// delay between every two refreshing event
	private static int delay;
	// pond that contain all fishes
	private final Pond pond;
	// slider to adjust the time elapsing speed
	private JSlider vslider;
	// all components in the menu bar
	private JMenuBar menuBar = new JMenuBar();
	private ArrayList<JMenu> menus = new ArrayList<JMenu>();
	private ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();
	// the background image
	private Image background = Toolkit.getDefaultToolkit().getImage(
			"res/sea.jpg");
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 25);

	// constructor
	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		setBackground(Color.CYAN);
		// set the menus
		menus.add(new JMenu("Edit"));
		menus.add(new JMenu("Window"));
		menus.add(new JMenu("Help"));
		for (JMenu menu : menus) {
			menu.setFont(UNIFIED_FONT);
			menu.setBackground(Color.WHITE);
		}
		// set the menu items
		items.add(new JMenuItem("add new fish"));
		items.add(new JMenuItem("reset all v and p"));
		items.add(new JMenuItem("remove all"));
		items.add(new JMenuItem("reset window size"));
		items.add(new JMenuItem("credit"));
		for (JMenuItem item : items) {
			item.addActionListener(this);
			item.setFont(UNIFIED_FONT);
		}
		// add items to menus
		menus.get(0).add(items.get(0));
		menus.get(0).add(items.get(1));
		menus.get(0).add(items.get(2));
		menus.get(1).add(items.get(3));
		menus.get(2).add(items.get(4));
		// add menus to the bar
		for (JMenu menu : menus) {
			menuBar.add(menu);
		}
		// add bar to the frame
		menuBar.setBackground(Color.WHITE);
		Main.controlFrame.setJMenuBar(menuBar);

		// slider that adjusts the time elapsing speed
		vslider = new JSlider(SwingConstants.HORIZONTAL, 0, 49, 40);
		vslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				PondPanel.delay = 50 - vslider.getValue();
			}
		});
		add(vslider);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		// resize the pond to match the size of panel
		pond.resize(getSize());
		// Keep updating
		pond.nextMove();
		pond.paint(g);
		try {
			Thread.sleep(PondPanel.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) a.getSource();
			switch (item.getText()) {
			case "add new fish":
				// disable the menu bar
				Main.controlFrame.setJMenuBar(null);
				// switch the the fish selection panel
				Main.controlFrame.switchPanel(this, new FishSelectPanel(pond));
				break;
			case "reset all v and p":
				for (Fish fish : pond.getFishes()) {
					fish.setX(FishGenerator.randX());
					fish.setY(FishGenerator.randY());
					fish.setVx(FishGenerator.randV(5));
					fish.setVy(FishGenerator.randV(5));
				}
				break;
			case "remove all":
				pond.getFishes().clear();
				break;
			case "reset window size":
				// unimplemented
				break;
			case "credit":
				// unimplemented
				break;
			default:
				System.out.println("Unknown menu item");
			}
		}
	}
}
