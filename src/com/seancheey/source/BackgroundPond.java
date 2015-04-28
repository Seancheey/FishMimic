package com.seancheey.source;

import java.awt.Image;

public class BackgroundPond {
	private static final ImagePond pond;
	public static Image SEA, MEADOW;

	static {
		pond = new ImagePond("res/background/");
		pond.put("sea.jpg", SEA);
		pond.put("meadow.jpg", MEADOW);
	}
}
