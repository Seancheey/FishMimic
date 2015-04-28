package com.seancheey.interfaces;

/**
 * A interface that describes the behavior of a container. those who use the
 * interface can only access the container by using iteration or add or remove.
 */
public interface Container<T> extends Iterable<T> {
	/** add an object to the container */
	void add(T object);

	/** remevan object from the container */
	void remove(T object);
}
