package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.Container;
import com.seancheey.HasImage;
import com.seancheey.Movable;

public abstract class Map implements HasImage, Container<Entity>, Serializable,
		Movable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient Image background;
	protected int width, height;
	private ArrayList<Entity> entities;

	@Override
	public Iterator<Entity> iterator() {
		return entities.iterator();
	}

	@Override
	public void add(Entity object) {
		entities.add(object);
	}

	@Override
	public void remove(Entity object) {
		entities.remove(object);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, width, height, null);
		for (Entity entity : this) {
			entity.paint(g);
		}
	}

	@Override
	public void performNext() {
		for (Entity entity : this) {
			entity.performNext();
		}
	}

}
