package com.seancheey;

import java.awt.Image;

public class FishPond {
	private static final ImagePond pond;
	public static Image YELLOW, GREEN, BROWN, BLUE, RED, GITHUB;

	static {
		pond = new ImagePond("/res/fish/");
		YELLOW = pond.get("yellow fish.png");
		BLUE = pond.get("green fish.png");
		BROWN = pond.get("brown fish 1.png");
		BLUE = pond.get("blue fish.png");
		RED = pond.get("red fish.png");
		GITHUB = pond.get("github.png");
	}
}
