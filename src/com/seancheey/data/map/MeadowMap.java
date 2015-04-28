package com.seancheey.data.map;

import java.util.ArrayList;

import com.seancheey.BackgroundPond;
import com.seancheey.data.Entity;
import com.seancheey.data.Map;
import com.seancheey.data.entity.Pond;

public class MeadowMap extends Map {
	private static final long serialVersionUID = 1L;

	public MeadowMap(int width, int height, ArrayList<Entity> entities) {
		super(width, height, BackgroundPond.MEADOW, entities);
	}

	public MeadowMap(int width, int height) {
		this(width, height, new ArrayList<Entity>());
		add(new Pond(300, 300, 100, 100, BackgroundPond.SEA,
				this));
	}

	@Override
	public void fetchLostImage() {
		background = BackgroundPond.MEADOW;
	}
}
