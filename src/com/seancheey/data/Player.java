package com.seancheey.data;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	/** the money that the player owns to buy fish */
	private int money;

	public Player(int money) {
		super();
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
