package com.seancheey.data;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class ImagePond {
	private transient static HashMap<Integer, Image> INT_TO_IMAGE = new HashMap<Integer, Image>();
	private transient static HashMap<String, Integer> STRING_TO_IMAGE = new HashMap<String, Integer>();

	static {
		put("background - sea", "res/background/sea.jpg");
	}

	public static boolean init() {
		return true;
	}

	public static boolean has(Image resource) {
		for (Image i : INT_TO_IMAGE.values()) {
			if (i == resource)
				return true;
		}
		return false;
	}

	public static void put(String name, Image resource) {
		int hash = resource.hashCode();
		STRING_TO_IMAGE.put(name, hash);
		INT_TO_IMAGE.put(hash, resource);
	}

	public static void put(String name, String url) {
		put(name, Toolkit.getDefaultToolkit().getImage(url));
	}

	public static Image get(int index) {
		return INT_TO_IMAGE.get(index);
	}

	public static Image get(String name) {
		return INT_TO_IMAGE.get(STRING_TO_IMAGE.get(name));
	}
}
