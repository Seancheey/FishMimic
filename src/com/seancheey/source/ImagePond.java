package com.seancheey.source;

import java.awt.Image;
import java.awt.Toolkit;

public class ImagePond {
	private String path;

	public ImagePond(String path) {
		this.path = path;
	}

	public void put(String source, Image image) {
		image = Toolkit.getDefaultToolkit().getImage((path + source));
		if (image.getWidth(null) <= 0)
			throw new NullPointerException("Image " + path + source
					+ " can't be found");
	}
}
