package com.seancheey.data;

import java.io.Serializable;

import com.seancheey.HasImage;

public abstract class Player implements Serializable, HasImage {
	private static final long serialVersionUID = 1L;
	/** the money that the player owns to buy fish */
	private int money;
	/** default generated fish's size */
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;

	public Player(int money) {
		this.money = money;
	}

	public void earnMoney(int amount) {
		money += amount;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void spendMoney(int amount) {
		money -= amount;
	}

}
