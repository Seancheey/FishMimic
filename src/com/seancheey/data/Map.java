package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.Container;
import com.seancheey.HasImage;
import com.seancheey.Performable;

/**
 * The container that includes all of the objects exist in the game. Player use
 * the map to communicate with the object in the map. The game panel includes
 * the instance of map that will be operated.
 */
public abstract class Map implements HasImage, Container<Entity>, Serializable,
		Performable {
	private static final long serialVersionUID = 1L;
	protected int width, height;
	protected transient Image background;
	protected Player owner;
	private ArrayList<Entity> entities;

	public Map(int width, int height, Image background, Player owner,
			ArrayList<Entity> entities) {
		super();
		this.width = width;
		this.height = height;
		this.background = background;
		this.owner = owner;
		this.entities = entities;
	}

	@Override
	public void add(Entity object) {
		entities.add(object);
	}

	public Image getBackground() {
		return background;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public Iterator<Entity> iterator() {
		return entities.iterator();
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

	@Override
	public void remove(Entity object) {
		entities.remove(object);
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
