package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Map;
import com.seancheey.interfaces.GUIContainer;
import com.seancheey.source.BackgroundPond;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	// Container of all the components
	private ArrayList<JButton> components = new ArrayList<JButton>();
	private JLabel title;
	/** unified font for all components */
	private static final Font UNIFIED_FONT = new Font("serif", Font.BOLD, 35);
	/** the container that has this panel */
	private GUIContainer guiContainer;

	public Menu(GUIContainer guiContainer) {
		this.guiContainer = guiContainer;
		setFocusable(false);
		title = new JLabel(Main.NAME);
		title.setFont(UNIFIED_FONT);
		title.setForeground(Color.MAGENTA);
		title.setAlignmentX(0.5f);
		title.setAlignmentY(0.5f);
		add(title);
		// add all needed components to the container
		components.add(new JButton("New Game"));
		components.get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				toNewGamePanel();
			}
		});
		components.add(new JButton("Read Progress"));
		components.get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				toLoadedGamePanel();
			}
		});
		components.add(new JButton("About us"));
		components.get(2).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				toAboutUs();
			}
		});
		// unify the font of components and add to this panel
		for (JButton c : components) {
			c.setFont(UNIFIED_FONT);
			c.setBackground(Color.WHITE);
			c.setFocusable(false);
			c.setAlignmentX(0.5f);
			c.setAlignmentY(0.5f);
			add(c);
		}
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		g.drawImage(BackgroundPond.SEA, 0, 0, getWidth(), getHeight(), this);
	}

	private Map readProgress() {
		JFileChooser fileChooser = new JFileChooser("dat");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showOpenDialog(null);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setAcceptAllFileFilterUsed(false);
		File file = fileChooser.getSelectedFile();
		FileInputStream filein;
		ObjectInputStream objin;
		Object obj = null;
		if (file != null) {
			try {
				filein = new FileInputStream(file);
				objin = new ObjectInputStream(filein);
				obj = objin.readObject();
				filein.close();
				objin.close();

			} catch (Exception e) {
			}
		}
		if (obj instanceof Map) {
			return (Map) obj;
		} else {
			return null;
		}
	}

	private void toAboutUs() {
		guiContainer.switchPanel(this, new CreditPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void backAction() {
				guiContainer.switchPanel(this, new Menu(guiContainer));
			}
		});
	}

	private void toLoadedGamePanel() {
		Map map = readProgress();
		if (map != null)
			guiContainer.switchPanel(this, new GamePanel(map));
	};

	private void toNewGamePanel() {
		guiContainer.switchPanel(this, new GamePanel());
	}
}
