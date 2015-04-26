package com.seancheey.data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.seancheey.data.entity.Fish;
import com.seancheey.data.entity.fish.MaoFish;
import com.seancheey.data.entity.fish.RainbowFish;
import com.seancheey.data.entity.fish.RectFish;
import com.seancheey.data.entity.fish.RoundFish;

public class Pond implements Serializable {
	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

	private static final long serialVersionUID = 2L;
	// default generated fish's size
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;
	// width and height of the pond
	private int width, height;
	// fish container
	private final ArrayList<Fish> fishes;
	// player that own the pond
	private Player player;
	// the list of fish waited to be added or removed
	private ArrayList<Fish> added = new ArrayList<Fish>(),
			removed = new ArrayList<Fish>();

	// simplified constructor
	public Pond(int width, int height) {
		this(width, height, new ArrayList<Fish>(), new Player(500));
	}

	// origin constructor
	public Pond(int width, int height, ArrayList<Fish> fishes, Player player) {
		super();
		this.width = width;
		this.height = height;
		this.fishes = fishes;
		this.player = player;
	}

	// add the fish to the waiting list
	public void add(Fish fish) {
		added.add(fish);
	}

	// buy a fish that cost money
	public void buy(Fish fish) {
		if (player.getMoney() >= fish.getPrice() + 10) {
			add(fish);
			player.spendMoney(fish.getPrice() + 10);
		}
	}

	// buy a fish that cost money
	public void buy(String type, int width, int height, double x, double y,
			double vx, double vy) {
		switch (type) {
		case "RectFish":
			buy(new RectFish(width, height, x, y, vx, vy, this));
			break;
		case "RoundFish":
			buy(new RoundFish(width, height, x, y, vx, vy, this));
			break;
		case "MaoFish":
			buy(new MaoFish(width, height, x, y, vx, vy, this));
			break;
		case "RainbowFish":
			buy(new RainbowFish(width, height, x, y, vx, vy, this));
			break;
		default:
			throw new IllegalArgumentException("Fish type mismatch! :" + type);
		}
	}

	// buy a default random fish and put it on a place
	public void buyFishAndPut(int x, int y) {
		switch (new java.util.Random().nextInt(4)) {
		case 0:
			buy(new RectFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x - DEFAULT_WIDTH
					/ 2, y - DEFAULT_HEIGHT / 2, randV(5), randV(5), this));
			break;
		case 1:
			buy(new RoundFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x - DEFAULT_WIDTH
					/ 2, y - DEFAULT_HEIGHT / 2, randV(5), randV(5), this));
			break;
		case 2:
			buy(new MaoFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x - DEFAULT_WIDTH
					/ 2, y - DEFAULT_HEIGHT / 2, randV(5), randV(5), this));
			break;
		case 3:
			buy(new RainbowFish(DEFAULT_WIDTH, DEFAULT_HEIGHT, x
					- DEFAULT_WIDTH / 2, y - DEFAULT_HEIGHT / 2, randV(5),
					randV(5), this));
			break;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pond other = (Pond) obj;
		if (fishes == null) {
			if (other.fishes != null)
				return false;
		} else if (!fishes.equals(other.fishes))
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	// return the first fish at the place
	public Fish getFishAt(int x, int y) {
		for (Fish fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < fish.getWidth() / 2
					&& Math.abs(y - fish.getYCenter()) < fish.getHeight() / 2)
				return fish;
		}
		return null;
	}

	public int getHeight() {
		return height;
	}

	public synchronized Iterator<Fish> getIterator() {
		return fishes.iterator();
	}

	public Player getPlayer() {
		return player;
	}

	public Fish getRandomFish() {
		return fishes.get((int) (Math.random() * fishes.size()));
	}

	public int getWidth() {
		return width;
	}

	// return the next fish of the argument in the array
	public Fish nextFish(Fish fish) {
		int index = fishes.indexOf(fish);
		if (index + 1 == fishes.size()) {
			return fishes.get(0);
		} else {
			return fishes.get(index + 1);
		}
	}

	// invoke next performance
	public synchronized void nextMove() {
		// add and remove the waiting fish
		fishes.addAll(added);
		for (Fish fish : removed) {
			fishes.remove(fish);
		}
		// chear the wait list
		added.clear();
		removed.clear();
		// invoke next performance
		Iterator<Fish> i = getIterator();
		while (i.hasNext()) {
			i.next().perform();
		}
	}

	// paint the pond itself with all fish
	public synchronized void paint(Graphics g) {
		Iterator<Fish> i = getIterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	// return random x form 0 to width
	public int randX() {
		return (int) (Math.random() * getWidth());
	}

	// return random y form 0 to height
	public int randY() {
		return (int) (Math.random() * getHeight());
	}

	// add the fish to the removal list
	public void remove(Fish fish) {
		removed.add(fish);
	}

	// reset all fish's velocity and position
	public void resetAll() {
		for (Fish fish : fishes) {
			fish.setX(randX());
			fish.setY(randY());
			fish.setVx(randV(5));
			fish.setVy(randV(5));
		}
	}

	// resize the width and height of the pond
	public synchronized void resize(Dimension dimension) {
		width = dimension.width;
		height = dimension.height;
	}

	// sell the fish and add money to the player
	public void sell(Fish fish) {
		player.earnMoney(fish.getPrice());
		removed.add(fish);
	}

	// sell all fish
	public void sellAll() {
		for (Fish fish : fishes) {
			sell(fish);
		}
	}
}
