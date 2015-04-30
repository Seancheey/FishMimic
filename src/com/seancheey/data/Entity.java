package com.seancheey.data;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;

import com.seancheey.interfaces.Container;
import com.seancheey.interfaces.HasImage;
import com.seancheey.interfaces.Performable;

public abstract class Entity implements Serializable, HasImage, Performable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// the location and velocity
	protected double width, height, x, y;
	// the image of the object
	protected transient Image image;
	// the trait of the image
	protected double rotation, shearX, shearY;

	// the container that holds the object
	protected final Container<Entity> container;

	public Entity(double width, double height, double x, double y, Image image,
			Container<Entity> container) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.image = image;
		this.container = container;
	}

	public Container<Entity> getContainer() {
		return container;
	}

	public double getHeight() {
		return height;
	}

	public Image getImage() {
		return image;
	}

	public double getRotation() {
		return rotation;
	}

	public double getShearX() {
		return shearX;
	}

	public double getShearY() {
		return shearY;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getXCenter() {
		return x + width / 2;
	}

	public double getY() {
		return y;
	}

	public double getYCenter() {
		return y + height / 2;
	}

	public boolean includePoint(Point p) {
		if (p.x > x && p.x < x + width && p.y > y && p.y < y + height)
			return true;
		else
			return false;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(rotation, getXCenter(), getYCenter());
		g2.shear(shearX, shearY);
		g2.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
		g2.shear(-shearX, -shearY);
		g2.rotate(-rotation, getXCenter(), getYCenter());
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public void setShearX(double shearX) {
		this.shearX = shearX;
	}

	public void setShearY(double shearY) {
		this.shearY = shearY;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
