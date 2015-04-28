package com.seancheey.source;

import java.awt.Image;
import java.awt.Toolkit;

public class ImagePond {
	private String path;

	public ImagePond(String path) {
		this.path = path;
	}

	public Image get(String source) {
		return Toolkit.getDefaultToolkit().getImage((path + source));
	}
}
