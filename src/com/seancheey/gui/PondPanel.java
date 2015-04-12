package com.seancheey.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.seancheey.Main;
import com.seancheey.data.Pond;

public class PondPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Pond pond;
	private JSlider vslider;

	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		setSize(Main.WIDTH, Main.HEIGHT);
		setBackground(Color.CYAN);

		// Keep updating
		new Thread() {
			public void run() {
				while (true) {
					try {
						sleep(Main.DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					PondPanel.this.repaint();
				}
			}
		}.start();

		vslider = new JSlider(SwingConstants.HORIZONTAL, 0, 49, 40);
		this.add(vslider);
		vslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				Main.DELAY = 50 - vslider.getValue();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		pond.nextMove();
		pond.paint(g);
	}
}
