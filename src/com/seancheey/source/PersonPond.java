package com.seancheey.source;

import java.awt.Image;

public class PersonPond {
	private static final ImagePond pond;
	public static Image COWBOY;
	static {
		pond = new ImagePond("res/person/");
		COWBOY = pond.get("cowboy.png");
	}
}
