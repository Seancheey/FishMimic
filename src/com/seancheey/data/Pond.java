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
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public static final int DEFAULT_WIDTH = 120, DEFAULT_HEIGHT = 60;
	private int width, height;// width and height of the pond
	private final ArrayList<Fish> fishes;// fish container
	private Player player;
	// the list of fish waited to be added or removed
	private ArrayList<Fish> added = new ArrayList<Fish>(),
			removed = new ArrayList<Fish>();

	// List of constructor
	public Pond(int width, int height, ArrayList<Fish> fishes, Player player) {
		super();
		this.width = width;
		this.height = height;
		this.fishes = fishes;
		this.player = player;
	}

	public Pond(int width, int height) {
		this(width, height, new ArrayList<Fish>(), new Player(500));
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

	// list of getter
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	public Fish getFishAt(int x, int y) {
		for (Fish fish : fishes) {
			if (Math.abs(x - fish.getXCenter()) < fish.getWidth() / 2
					&& Math.abs(y - fish.getYCenter()) < fish.getHeight() / 2)
				return fish;
		}
		return null;
	}

	public Fish getRandomFish() {
		return fishes.get((int) (Math.random() * fishes.size()));
	}

	// list of setter
	public synchronized void resize(Dimension dimension) {
		width = dimension.width;
		height = dimension.height;
	}

	public synchronized ArrayList<Fish> getFishes() {
		return fishes;
	}

	public synchronized Iterator<Fish> getIterator() {
		return fishes.iterator();
	}

	public void remove(Fish fish) {
		removed.add(fish);
	}

	public void add(Fish fish) {
		added.add(fish);
	}

	public void buy(Fish fish) {
		if (player.getMoney() >= fish.getPrice() + 10) {
			add(fish);
			player.spendMoney(fish.getPrice() + 10);
		}
	}

	public void sell(Fish fish) {
		player.earnMoney(fish.getPrice());
		removed.add(fish);
	}

	public void sellAll() {
		for (Fish fish : fishes) {
			sell(fish);
		}
	}

	public int randX() {
		return (int) (Math.random() * getWidth());
	}

	public int randY() {
		return (int) (Math.random() * getHeight());
	}

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

	public static final double randV(double range) {
		return Math.random() * range * 2 - range;
	}

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

	// paint self
	public synchronized void paint(Graphics g) {
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	public synchronized void nextMove() {
		// add and remove the waiting fish
		fishes.addAll(added);
		for (Fish fish : removed) {
			fishes.remove(fish);
		}
		// chear the wait list
		added.clear();
		removed.clear();
		// invoke next perform
		Iterator<Fish> i = getFishes().iterator();
		while (i.hasNext()) {
			i.next().perform();
		}
	}
}
