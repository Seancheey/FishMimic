package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.data.entity.MovingEntity;
import com.seancheey.interfaces.Container;
import com.seancheey.interfaces.HasImage;
import com.seancheey.interfaces.MouseControllable;
import com.seancheey.interfaces.Performable;

/**
 * The container that includes all of the objects exist in the game. Player use
 * the map to communicate with the object in the map. The game panel includes
 * the instance of map that will be operated.
 */
public abstract class Map implements HasImage, Container<Entity>, Serializable,
		Performable, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	/** the size of the map */
	protected int width, height;

	/** the background of the map */
	protected transient Image background;
	/** the player that own the map */
	protected Player owner;
	/** contains all the entities in the map */
	private ArrayList<Entity> entities;
	/** the target entity the mouse is currently dragging */
	private Entity draggedEntity;

	/** full constructor */
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

	public ArrayList<Entity> getEntitiesAt(Point p) {
		ArrayList<Entity> x = new ArrayList<Entity>();
		for (Entity control : entities) {
			if (control.includePoint(p))
				x.add(control);
		}
		return x;
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
	public void keepElementsInside() {
		for (Entity entity : entities) {
			if (entity.getX() < 0) {
				if (entity instanceof MovingEntity)
					((MovingEntity) entity).setVx(-((MovingEntity) entity)
							.getVx());
				entity.setX(0);
			}
			if (entity.getY() < 0) {
				if (entity instanceof MovingEntity)
					((MovingEntity) entity).setVy(-((MovingEntity) entity)
							.getVy());
				entity.setY(0);
			}
			if (entity.getX() + entity.getWidth() > width) {
				if (entity instanceof MovingEntity)
					((MovingEntity) entity).setVx(-((MovingEntity) entity)
							.getVx());
				entity.setX(width - entity.getWidth());
			}
			if (entity.getY() + entity.getHeight() > height) {
				if (entity instanceof MovingEntity)
					((MovingEntity) entity).setVy(-((MovingEntity) entity)
							.getVy());
				entity.setY(height - entity.getHeight());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (Entity entity : getEntitiesAt(e.getPoint())) {
			if (entity instanceof MouseControllable)
				((MouseControllable) entity).mouseClicked(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (draggedEntity != null && draggedEntity instanceof MouseControllable)
			((MouseControllable) draggedEntity).mouseDragged(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getModifiers() == 16)
			for (Entity entity : getEntitiesAt(e.getPoint())) {
				if (entity instanceof MouseControllable) {
					draggedEntity = entity;
					break;
				}
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		draggedEntity = null;
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
		keepElementsInside();
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
