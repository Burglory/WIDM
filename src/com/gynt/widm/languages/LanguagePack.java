package com.gynt.widm.languages;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gynt.widm.GUI;

public class LanguagePack {

	public static String FILE = "File";
	public static String OPEN = "Open...";
	public static String SAVE = "Save";
	public static String SAVE_AS = "Save as...";
	public static String EXIT = "Exit";
	
	public static void loadDefault() {
		try {
			URL url = GUI.class.getClassLoader().getResource("com/gynt/widm/languages/english.lang");
			System.out.println(url);
			URI uri = url.toURI();
			Path path = Paths.get(uri);
			load(path);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void load(Path path) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<String> lines;
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for(String line : lines) {
				String[] lineparts = line.split("=", 2);
				map.put(lineparts[0], lineparts[1]);
			}
			for(Field f : LanguagePack.class.getDeclaredFields()) {
				if(map.containsKey(f.getName())) {
					try {
						f.set(null, map.get(f.getName()));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
