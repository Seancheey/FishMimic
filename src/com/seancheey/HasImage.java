package com.seancheey;

import java.awt.Graphics;

public interface HasImage {
	/** get the image back when serialized object is recovered */
	void fetchLostImage();

	/** paint the image by the graphics */
	void paint(Graphics g);
}
