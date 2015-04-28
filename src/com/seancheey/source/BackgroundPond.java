package com.seancheey.source;

import java.awt.Image;

public class BackgroundPond {
	private static final ImagePond pond;
	public static Image SEA, MEADOW;

	static {
		pond = new ImagePond("res/background/");
		SEA = pond.get("sea.jpg");
		MEADOW = pond.get("meadow.jpg");
	}
}
