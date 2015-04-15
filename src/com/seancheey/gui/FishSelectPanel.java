package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.seancheey.Main;
import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;

public class FishSelectPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 20);
	// all of the components contained in the panel
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
	private ArrayList<JRadioButton> radios = new ArrayList<JRadioButton>();
	private ButtonGroup typeGroup = new ButtonGroup();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();

	// constructor
	public FishSelectPanel() {
		setSize(Main.WIDTH, Main.HEIGHT);
		setLocation(0, 0);
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
		labels.add(new JLabel("number"));
		// unify and add all to the panel
		for (int i = 0; i < labels.size(); i++) {
			fields.add(new JTextField("0", 3));
			labels.get(i).setFont(UNIFIED_FONT);
			fields.get(i).setFont(UNIFIED_FONT);
			this.add(labels.get(i));
			this.add(fields.get(i));
		}
		// set default editable to some fields as false
		fields.get(2).setEditable(false);
		fields.get(3).setEditable(false);
		fields.get(4).setEditable(false);
		fields.get(5).setEditable(false);
		fields.get(6).setEditable(false);
		fields.get(7).setEditable(false);
		// default value for width and height
		fields.get(0).setText("40");
		fields.get(1).setText("20");
		fields.get(9).setText("100");
		// the check box of fast creation
		checks.add(new JCheckBox("random position"));
		checks.add(new JCheckBox("random speed"));
		checks.add(new JCheckBox("random color"));
		// unify and add all to the panel and set default as true
		for (JCheckBox o : checks) {
			o.setFont(UNIFIED_FONT);
			o.addActionListener(this);
			o.setSelected(true);
			this.add(o);
		}

		// the radio buttons of the type available for selection
		radios.add(new JRadioButton("Rectangular Fish"));
		radios.add(new JRadioButton("Round Fish"));
		radios.add(new JRadioButton("Follower Fish"));
		radios.add(new JRadioButton("Guider Fish"));
		radios.add(new JRadioButton("Cat Fish"));
		// unify and add all to the panel and a single button group
		for (int i = 0; i < radios.size(); i++) {
			radios.get(i).setFont(UNIFIED_FONT);
			typeGroup.add(radios.get(i));
			this.add(radios.get(i));
		}
		// set a default value
		radios.get(0).setSelected(true);
		// the buttons of all in the panel
		buttons.add(new JButton("Add"));
		buttons.add(new JButton("Back"));
		// unify and add all the buttons
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setFont(UNIFIED_FONT);
			buttons.get(i).setBackground(Color.WHITE);
			buttons.get(i).addActionListener(this);
			this.add(buttons.get(i));
		}
		// set the layout to grid layout
		setLayout(new GridLayout(8, 2));
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton) {
			switch (((JButton) a.getSource()).getText()) {
			case "Add":
				// find the selected type of fish
				JRadioButton selected = null;
				for (int i = 0; i < radios.size(); i++) {
					if (radios.get(i).isSelected()) {
						selected = radios.get(i);
						break;
					}
				}
				// convert the selected type to fit type in Fish Generatord
				String type;
				switch (selected.getText()) {
				case "Rectangular Fish":
					type = "RectFish";
					break;
				case "Round Fish":
					type = "RoundFish";
					break;
				case "Follower Fish":
					type = "FollowerFish";
					break;
				case "Guider Fish":
					type = "GuideFish";
					break;
				case "Cat Fish":
					type = "MaoFish";
					break;
				default:
					type = "Not a fish";
				}
				// options of randomization
				boolean randP = checks.get(0).isSelected(),
				randV = checks.get(1).isSelected(),
				randC = checks.get(2).isSelected();
				// parameters of fish
				int width = Integer.valueOf(fields.get(0).getText()),
				height = Integer.valueOf(fields.get(1).getText()),
				x = Integer.valueOf(fields.get(2).getText()),
				y = Integer.valueOf(fields.get(3).getText()),
				vx = Integer.valueOf(fields.get(4).getText()),
				vy = Integer.valueOf(fields.get(5).getText()),
				cr = Integer.valueOf(fields.get(6).getText()),
				cg = Integer.valueOf(fields.get(7).getText()),
				cb = Integer.valueOf(fields.get(8).getText()),
				createNum = Integer.valueOf(fields.get(9).getText());
				// initialize a new pond
				Pond p = new Pond(Main.WIDTH, Main.HEIGHT);
				// add the number of fish equal to the inputed
				for (int i = 0; i < createNum; i++) {
					p.getFishes().add(
							FishGenerator.generate(type, width, height,
									randP ? FishGenerator.randX() : x,
									randP ? FishGenerator.randY() : y,
									randV ? FishGenerator.randV(5) : vx,
									randV ? FishGenerator.randV(5) : vy, p,
									randC ? FishGenerator.randColor()
											: new Color(cr, cg, cb)));
				}
				// switch to Pond panel
				Main.controlFrame.switchPanel(this, new PondPanel(p));
				break;
			case "Back":
				Main.controlFrame.switchPanel(this, new Menu());
				break;
			}
		} else if (a.getSource() instanceof JCheckBox) {
			// create the object same to the source:eases using
			JCheckBox c = (JCheckBox) (a.getSource());
			switch (c.getText()) {
			case "random position":
				// enable or disable the x,y text fields
				if (c.isSelected()) {
					fields.get(2).setEditable(false);
					fields.get(3).setEditable(false);
				} else {
					fields.get(2).setEditable(true);
					fields.get(3).setEditable(true);
				}
				break;
			case "random speed":
				// enable or disable the vx,vy text fields
				if (c.isSelected()) {
					fields.get(4).setEditable(false);
					fields.get(5).setEditable(false);
				} else {
					fields.get(4).setEditable(true);
					fields.get(5).setEditable(true);
				}
				break;
			case "random color":
				// enable or disable the color tet fields
				if (c.isSelected()) {
					fields.get(6).setEditable(false);
					fields.get(7).setEditable(false);
					fields.get(8).setEditable(false);
				} else {
					fields.get(6).setEditable(true);
					fields.get(7).setEditable(true);
					fields.get(8).setEditable(true);
				}
				break;
			}
		}
	}
}
