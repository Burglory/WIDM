package com.gynt.widm.core;

import java.io.IOException;

import com.gynt.easysettings.Settings;
import com.gynt.easysettings.Settings.Dir;

public class Preferences {

	public static Settings settings = new Settings(null);
	public static Dir ROOT = settings.getRoot();
	
	private Game game;
	
	public Preferences(Game g) {
		game=g;
	}

	public void load() throws IOException {
		settings.fromBytes(game.fileinterface.retrieve("preferences.properties"));
	}
	
	public void save() throws IOException {
		game.fileinterface.store(settings.getBytes(), "preferences.properties");
	}
	
}
