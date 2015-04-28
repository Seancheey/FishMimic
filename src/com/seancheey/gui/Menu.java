package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.seancheey.BackgroundPond;
import com.seancheey.GUIContainer;
import com.seancheey.Main;
import com.seancheey.data.Map;

public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Container of all the components
	private ArrayList<JComponent> components = new ArrayList<JComponent>();
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.BOLD, 35);
	private GUIContainer guiContainer;

	public Menu(GUIContainer guiContainer) {
		this.guiContainer = guiContainer;
		setFocusable(false);
		// add all needed components to the container
		components.add(new JLabel(Main.NAME));
		components.add(new JButton("New Game"));
		components.add(new JButton("Read Progress"));
		components.add(new JButton("About us"));
		// unify the font of components and add to this panel
		for (JComponent c : components) {
			if (c instanceof JButton) {
				((JButton) c).setFont(UNIFIED_FONT);
				((JButton) c).addActionListener(this);
				((JButton) c).setBackground(Color.WHITE);
			} else if (c instanceof JLabel) {
				((JLabel) c).setFont(UNIFIED_FONT);
				((JLabel) c).setForeground(Color.MAGENTA);
			}
			c.setFocusable(false);
			add(c);
		}
		// create grid bag layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridwidth = 0;
		c.weightx = 1;
		c.weighty = 1;
		for (JComponent component : components) {
			layout.setConstraints(component, c);
		}
		setLayout(layout);
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

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			JButton b = (JButton) a.getSource();
			switch (b.getText()) {
			case "New Game":
				guiContainer.switchPanel(this, new GamePanel());
				break;
			case "Read Progress":
				guiContainer.switchPanel(this, new GamePanel(readProgress()));
				break;
			case "About us":
				guiContainer.switchPanel(this, new CreditPanel() {
					private static final long serialVersionUID = 1L;

					@Override
					public void backAction() {
						guiContainer.switchPanel(this, new Menu(guiContainer));
					}
				});
				break;
			}
		}
	};

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		g.drawImage(BackgroundPond.SEA, 0, 0, getWidth(), getHeight(), this);
	}
}
