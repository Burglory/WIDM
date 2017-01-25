package com.gynt.widm.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Preferences {

	public static Game game;
	public static Properties map = new Properties();

	public static Boolean getBoolean(String key) {
		try {
			return Boolean.parseBoolean(map.getProperty(key));
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer getInteger(String key) {
		try {
			return Integer.parseInt(map.getProperty(key));
		} catch (Exception e) {
			return null;
		}
	}

	public static String get(String key) {
		return map.getProperty(key);
	}

	public static boolean has(String key) {
		return map.containsKey(key);
	}

	public static void set(String key, String value) {
		map.setProperty(key, value);
	}

	// private static String serialize() {
	// String result = "";
	// for(Entry<String, String> entry : map.entrySet()) {
	// result+=entry.getKey()+"="+entry.getValue()+"\n";
	// }
	// return result;
	// }
	//
	// private static void deserialize(String data) {
	// map= new HashMap<String, String>();
	// for(String line : data.split("(\n|\r)+")) {
	// if(line.isEmpty()) continue;
	// String[] split = line.split(" *= *");
	// if(split.length!=2 || split[0].isEmpty() || split[1].isEmpty()) continue;
	// map.put(split[0], split[1]);
	// }
	// }

	public static void save() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		map.store(bos, "");
		game.fileinterface.store(bos.toByteArray(), "preferences.properties");
	}

	public static void load() throws IOException {
		map = new Properties();
		if (game.fileinterface != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(game.fileinterface.retrieve("preferences.properties"));
			map.load(bis);
		}

	}

	public static boolean loaded() {
		return game != null && map != null;
	}

}
