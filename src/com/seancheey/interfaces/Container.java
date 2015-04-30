package com.seancheey.interfaces;

/**
 * A interface that describes the behavior of a container. those who use the
 * interface can only access the container by using iteration or add or remove.
 */
public interface Container<T> extends Iterable<T> {
	/** add an object to the container */
	void add(T object);

	/** method to keep all elements inside */
	void keepElementsInside();

	/** remevan object from the container */
	void remove(T object);
}
