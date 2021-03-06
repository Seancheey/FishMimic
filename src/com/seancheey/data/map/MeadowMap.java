package com.seancheey.data.map;

import java.util.ArrayList;

import com.seancheey.data.Entity;
import com.seancheey.data.Map;
import com.seancheey.data.Player;
import com.seancheey.data.entity.Pond;
import com.seancheey.data.movingEntity.person.Cowboy;
import com.seancheey.source.BackgroundPond;

public class MeadowMap extends Map {
	private static final long serialVersionUID = 1L;

	@Deprecated
	/**Just for a test*/
	public MeadowMap(int width, int height) {
		this(width, height, null, new ArrayList<Entity>());
		add(new Pond(300, 300, 100, 100, BackgroundPond.SEA, this));
		add(new Cowboy(40, 60, 50, 50, this, 0, 0.2));
	}

	public MeadowMap(int width, int height, Player owner,
			ArrayList<Entity> entities) {
		super(width, height, BackgroundPond.MEADOW, owner, entities);
	}

	@Override
	public void fetchLostImage() {
		background = BackgroundPond.MEADOW;
	}
}
