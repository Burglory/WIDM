package com.gynt.widm.core;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;

public class Preferences {

	private static Game game;
	private static HashMap<String, String> map;

	public static String get(String key) {
		return map.get(key);
	}

	public static boolean has(String key) {
		return map.containsKey(key);
	}

	public static void set(String key, String value) {
		map.put(key, value);
	}

	private static String serialize() {
		String result = "";
		for(Entry<String, String> entry : map.entrySet()) {
			result+=entry.getKey()+"="+entry.getValue()+"\n";
		}
		return result;
	}

	private static void deserialize(String data) {
		map= new HashMap<String, String>();
		for(String line : data.split("(\n|\r)+")) {
			if(line.isEmpty()) continue;
			String[] split = line.split(" *= *");
			if(split.length!=2 || split[0].isEmpty() || split[1].isEmpty()) continue;
			map.put(split[0], split[1]);
		}
	}

	public static void save() throws IOException {
		game.fileinterface.save(serialize().getBytes(Charset.forName("UTF-8")), game.fileinterface.getRoot().resolve("preferences"));
	}

	public static void load(Game g) throws IOException {
		String data = new String(g.fileinterface.load(g.fileinterface.getRoot().resolve("preferences")), Charset.forName("UTF-8"));
		deserialize(data);
		game=g;
	}

	public static boolean loaded() {
		return game!=null && map!=null;
	}

}
