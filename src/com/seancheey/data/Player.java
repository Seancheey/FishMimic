package com.seancheey.data;

import java.io.Serializable;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int money;

	public Player(int money) {
		super();
		this.money = money;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	public void spendMoney(int amount) {
		money -= amount;
	}

	public void earnMoney(int amount) {
		money += amount;
	}

}
