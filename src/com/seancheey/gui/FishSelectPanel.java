package com.seancheey.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.seancheey.Main;

public class FishSelectPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
	private ArrayList<JRadioButton> radios = new ArrayList<JRadioButton>();
	private ButtonGroup typeGroup = new ButtonGroup();

	public FishSelectPanel() {
		setSize(Main.WIDTH, Main.HEIGHT);
		setLocation(0,0);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// the tags of all parameters received
		labels.add(new JLabel("width"));
		labels.add(new JLabel("height"));
		labels.add(new JLabel("position-x"));
		labels.add(new JLabel("position-y"));
		labels.add(new JLabel("speed-x"));
		labels.add(new JLabel("speed-y"));
		labels.add(new JLabel("color-r"));
		labels.add(new JLabel("color-g"));
		labels.add(new JLabel("color-b"));
		// create the same number of text fields
		for (int i = 0; i < labels.size(); i++) {
			fields.add(new JTextField("0",3));
		}
		// add all to the panel
		for (int i = 0; i < labels.size(); i++) {
			this.add(labels.get(i));
			this.add(fields.get(i));
		}
		// the check box of fast creation
		checks.add(new JCheckBox("random position"));
		checks.add(new JCheckBox("random speed"));
		checks.add(new JCheckBox("random color"));
		// add all to the panel
		for (int i = 0; i < checks.size(); i++) {
			this.add(checks.get(i));
		}
		// the radio buttons of the type available for selection
		radios.add(new JRadioButton("Rectangle fish"));
		radios.add(new JRadioButton("Oval fish"));
		radios.add(new JRadioButton("Follower fish"));
		radios.add(new JRadioButton("Guider fish"));
		// add all to the panel and a single button group
		for (int i = 0; i < radios.size(); i++) {
			typeGroup.add(radios.get(i));
			this.add(radios.get(i));
		}
		this.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			// unimplemented
		}
	}

}
