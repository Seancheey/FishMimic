package com.seancheey.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	/** the money that the player owns to buy fish */
	private int money;
	/** the pond that player can access */
	private ArrayList<Pond> ponds;
	/** price that buying will add */
	private int addedValue = 10;

	public Player(int money) {
		this(money, new ArrayList<Pond>());
	}

	public Player(int money, ArrayList<Pond> ponds) {
		super();
		this.money = money;
		this.ponds = ponds;
	}
	
	public int getAddedValue() {
		return addedValue;
	}

	public void setAddedValue(int addedValue) {
		this.addedValue = addedValue;
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
