package com.gynt.widm.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class Preferences {

	private static Game game;
	private static Properties map;

	public static String get(String key) {
		return map.getProperty(key);
	}

	public static boolean has(String key) {
		return map.containsKey(key);
	}

	public static void set(String key, String value) {
		map.setProperty(key, value);
	}

//	private static String serialize() {
//		String result = "";
//		for(Entry<String, String> entry : map.entrySet()) {
//			result+=entry.getKey()+"="+entry.getValue()+"\n";
//		}
//		return result;
//	}
//
//	private static void deserialize(String data) {
//		map= new HashMap<String, String>();
//		for(String line : data.split("(\n|\r)+")) {
//			if(line.isEmpty()) continue;
//			String[] split = line.split(" *= *");
//			if(split.length!=2 || split[0].isEmpty() || split[1].isEmpty()) continue;
//			map.put(split[0], split[1]);
//		}
//	}

	public static void save() throws IOException {
		map.store(Files.newBufferedWriter(game.fileinterface.getRoot().resolve("preferences.properties")), "");
		//game.fileinterface.save(serialize().getBytes(Charset.forName("UTF-8")), game.fileinterface.getRoot().resolve("preferences"));
	}

	public static void load(Game g) throws IOException {
		game=g;
		map = new Properties();
		map.load(Files.newBufferedReader(game.fileinterface.getRoot().resolve("preferences.properties")));
//		String data = new String(g.fileinterface.load(g.fileinterface.getRoot().resolve("preferences")), Charset.forName("UTF-8"));
//		deserialize(data);

	}

	public static boolean loaded() {
		return game!=null && map!=null;
	}

}
