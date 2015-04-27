package com.seancheey.data;

import java.io.Serializable;

import com.seancheey.HasImage;
import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.Pond;

public abstract class Player implements Serializable, HasImage {
	private static final long serialVersionUID = 1L;
	/** the money that the player owns to buy fish */
	private int money;
	/** price that buying will add */
	private int addedValue = 10;
	/** default generated fish's size */
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;

	public Player(int money) {
		this.money = money;
	}

	/** buy a fish that cost money */
	public void buy(Fish fish, int width, int height, double x, double y,
			double vx, double vy, Pond pond) {
		fish.reset(width, height, x, y, vx, vy);
		buy(fish, pond);
	}

	/** buy a fish that cost money */
	public void buy(Fish fish, Pond pond) {
		if (money >= fish.getPrice() + 10) {
			fish.setPond(pond);
			pond.add(fish);
			spendMoney(fish.getPrice() + 10);
		}
	}

	/** buy a default random fish and put it on a place */
	public void buyFishAndPut(int x, int y, Pond pond) {
		Fish f = pond.getRandomFish().clone();
		f.reset(DEFAULT_WIDTH, DEFAULT_HEIGHT, x - DEFAULT_WIDTH / 2,
				y = DEFAULT_HEIGHT / 2, Pond.randV(5), Pond.randV(5));
		buy(f, pond);
	}

	public void earnMoney(int amount) {
		money += amount;
	}

	public int getAddedValue() {
		return addedValue;
	}

	public int getMoney() {
		return money;
	}

	public void setAddedValue(int addedValue) {
		this.addedValue = addedValue;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void spendMoney(int amount) {
		money -= amount;
	}

}
