package com.gynt.widm.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.gynt.easysettings.Settings;
import com.gynt.easysettings.Settings.Dir;
import com.gynt.widm.io.Serialization;

public class GlobalSettings {

	public static File loadFile;
	public static File saveFile;
	public static Settings settings;
	public static Dir ROOT;
	
	static {
		try {
			loadFile = Paths.get(Serialization.PATH_LOADER.getResource("").toURI()).resolve("settings.properties").toFile();
			saveFile = Paths.get(Serialization.PATH_LOADER.getResource("").toURI()).resolve("settings.properties").toFile();
			settings = new Settings(loadFile, saveFile);
			if(!loadFile.exists()) settings.saveDefault();
			ROOT = settings.getRoot();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void save() throws IOException {
		settings.save();
	}
	
	public static void load() throws IOException {
		settings.load();
	}

	
}
