package com.seancheey.data;

import java.io.Serializable;

import com.seancheey.interfaces.HasImage;

public abstract class Player implements Serializable, HasImage {
	private static final long serialVersionUID = 1L;
	/** the money that the player owns to buy fish */
	private int money;
	/** the name of the player */
	private String name;
	/** default generated fish's size */
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;

	public Player(int money, String name) {
		super();
		this.money = money;
		this.name = name;
	}

	public void earnMoney(int amount) {
		money += amount;
	}

	public int getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void spendMoney(int amount) {
		money -= amount;
	}

}
