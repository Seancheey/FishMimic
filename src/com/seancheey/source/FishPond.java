package com.seancheey.source;

import java.awt.Image;

public class FishPond {
	private static final ImagePond pond;
	public static Image YELLOW, GREEN, BROWN, BLUE, RED, GITHUB;

	static {
		pond = new ImagePond("/res/fish/");
		pond.put("yellow fish.png", YELLOW);
		pond.put("green fish.png", GREEN);
		pond.put("brown fish 1.png", BROWN);
		pond.put("blue fish.png", BLUE);
		pond.put("red fish.png", RED);
		pond.put("github.png", GITHUB);
	}
}
