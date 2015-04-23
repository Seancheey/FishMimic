package com.seancheey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import com.seancheey.data.FishGenerator;
import com.seancheey.data.Pond;
import com.seancheey.gui.fishSelectPanel.PondGetter;

public abstract class FishSelectPanel extends JPanel implements ActionListener,
		PondGetter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// unified font for all components
	private static final Font UNIFIED_FONT = new Font("serif", Font.PLAIN, 30);
	// all of the components contained in the panel
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
	private ArrayList<JRadioButton> radios = new ArrayList<JRadioButton>();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	// button group to bind radio buttons
	private ButtonGroup typeGroup = new ButtonGroup();
	// the pond that created fish will be added to
	private Pond pond;

	// constructor
	public FishSelectPanel(Pond pond) {
		this.pond = pond;
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
		fields.get(8).setEditable(false);
		// default value for width and height
		fields.get(0).setText("80");
		fields.get(1).setText("40");
		fields.get(9).setText("50");
		// the check box of fast creation
		checks.add(new JCheckBox("random position"));
		checks.add(new JCheckBox("random speed"));
		checks.add(new JCheckBox("random color"));
		// unify and add all to the panel and set default as true
		for (JCheckBox checkBox : checks) {
			checkBox.setFont(UNIFIED_FONT);
			checkBox.addActionListener(this);
			checkBox.setSelected(true);
			this.add(checkBox);
		}
		// the radio buttons of the type available for selection
		radios.add(new JRadioButton("Rectangular Fish"));
		radios.add(new JRadioButton("Round Fish"));
		radios.add(new JRadioButton("Follower Fish"));
		radios.add(new JRadioButton("Guider Fish"));
		radios.add(new JRadioButton("Cat Fish"));
		radios.add(new JRadioButton("Rainbow Fish"));
		// unify and add all to the panel and a single button group
		for (JRadioButton radio : radios) {
			radio.setFont(UNIFIED_FONT);
			typeGroup.add(radio);
			this.add(radio);
		}
		// set a default value
		radios.get(0).setSelected(true);
		// the buttons of all in the panel
		buttons.add(new JButton("Add"));
		buttons.add(new JButton("Back"));
		// unify and add all the buttons
		for (JButton button : buttons) {
			button.setFont(new Font("serif", Font.BOLD, 40));
			button.setBackground(Color.WHITE);
			button.addActionListener(this);
			this.add(button);
		}
		setGridBagLayout();
	}

	private void setGridBagLayout() {
		// create grid bag layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		setLayout(layout);
		// set layout for labels and fields
		for (int i = 0; i < labels.size(); i++) {
			if (i % 2 == 0) {
				c.gridwidth = 1;
				layout.setConstraints(labels.get(i), c);
				layout.setConstraints(fields.get(i), c);
			} else {
				c.gridwidth = 1;
				layout.setConstraints(labels.get(i), c);
				c.gridwidth = 0;
				layout.setConstraints(fields.get(i), c);
			}
		}
		// set layout for check boxes
		for (int i = 0; i < checks.size(); i++) {
			c.gridwidth = 1;
			if (i % 4 == 3 || i == checks.size() - 1)
				c.gridwidth = 0;
			layout.setConstraints(checks.get(i), c);
		}
		// set layout for radio buttons
		for (int i = 0; i < radios.size(); i++) {
			c.gridwidth = 1;
			if (i % 4 == 3 || i == radios.size() - 1)
				c.gridwidth = 0;
			layout.setConstraints(radios.get(i), c);
		}
		// set layout for buttons
		for (int i = 0; i < buttons.size(); i++) {
			c.gridwidth = 1;
			layout.setConstraints(buttons.get(i), c);
		}
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
				// convert the selected type to fit type in Fish Generator
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
				case "Rainbow Fish":
					type = "RainbowFish";
					break;
				default:
					type = "Not a fish";
				}
				// create variables representing options of randomization
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
				// initialize a new pond if don't have one
				if (pond == null)
					pond = new Pond(getWidth(), getHeight());
				// add the number of fish equal to the inputed
				FishGenerator gen = new FishGenerator(pond);
				for (int i = 0; i < createNum; i++) {
					// insert at random place
					pond.getFishes().add(
							(int) (pond.getFishes().size() * Math.random()),
							gen.generate(type, width, height,
									randP ? FishGenerator.randX() : x,
									randP ? FishGenerator.randY() : y,
									randV ? FishGenerator.randV(5) : vx,
									randV ? FishGenerator.randV(5) : vy,
									randC ? FishGenerator.randColor()
											: new Color(cr, cg, cb)));
				}
				// switch to Pond panel
				addAction();
				break;
			case "Back":
				backAction();
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
				// enable or disable the color text fields
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

	public abstract void addAction();

	public abstract void backAction();

	@Override
	public Pond getPond() {
		return pond;
	}
}
