package com.seancheey.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.seancheey.Main;
import com.seancheey.data.Pond;

public class PondPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Pond pond;

	public PondPanel(Pond pond) {
		super();
		this.pond = pond;
		setSize(Main.WIDTH, Main.HEIGHT);
		setBackground(new Color(0,170,220));
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.setColor(new Color(150,0,0));
		pond.nextMove();
		pond.paint(arg0);
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

}
