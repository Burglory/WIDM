package com.gynt.widm.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import com.gynt.widm.io.Serialization;

import java.util.Properties;
import java.util.ResourceBundle;

import sun.security.util.Resources;

public class Settings {

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

	public static void save() {
		try {
			map.store(new PrintWriter(Serialization.LOADER.getResource("settings.properties").toString()), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void load(String data) {
		map = new Properties();
		try {
			map.load(Serialization.LOADER.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static boolean loaded() {
		return map!=null;
	}

	public Settings() {

	}

}
