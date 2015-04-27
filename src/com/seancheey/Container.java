package com.seancheey;

public interface Container<T> extends Iterable<T> {
	void add(T object);

	void remove(T object);
}
