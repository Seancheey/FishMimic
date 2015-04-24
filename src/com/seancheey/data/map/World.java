package com.seancheey.data.map;

import java.awt.Image;
import java.util.ArrayList;

import com.seancheey.data.LocationComponent;
import com.seancheey.data.Map;

public class World extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public World(Image background, ArrayList<LocationComponent> locations) {
		super(background, locations);
	}

}
