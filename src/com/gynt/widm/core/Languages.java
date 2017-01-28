package com.gynt.widm.core;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gynt.widm.core.Settings.ChangeListener;
import com.gynt.widm.core.Settings.PreferenceSub;
import com.gynt.widm.core.Settings.Radio;
import com.gynt.widm.io.Serialization;

public class Languages {
	
	private static PreferenceSub language;
	
	static {
		language = Settings.ROOT.registerDir("General").registerSub("Language","Language");
	}

	private static HashMap<String, ResourceBundle> bundlemap = new HashMap<>();
	
	public static ResourceBundle LANGUAGE;
	
	private static void initializeCustom(URL langurl) {
		
		try {
			Path folder = Paths.get(langurl.toURI());
			File f = folder.toFile();
			File[] files = f.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isFile() && pathname.toString().endsWith(".properties");
				}
			});
			for(File file : files) {
				String lang = file.getName().split("[.]")[0];
				bundlemap.put(lang, ResourceBundle.getBundle("languages."+lang, Locale.getDefault(), Serialization.PATH_LOADER));
				language.registerItem(lang, lang, Radio.class, Boolean.TRUE).listeners.add(new ChangeListener() {
					
					@Override
					public void onChange(Object oldValue, Object newValue) {
						if(newValue==Boolean.TRUE) {
							LANGUAGE=bundlemap.get(lang);
						}
					}
				});
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void initializeBuiltin(String lang) {
		bundlemap.put(lang, ResourceBundle.getBundle("languages."+lang));
		LANGUAGE=bundlemap.get(lang);
		language.registerItem(lang, "English", Radio.class, Boolean.TRUE).listeners.add(new ChangeListener() {
			
			@Override
			public void onChange(Object oldValue, Object newValue) {
				if(newValue==Boolean.TRUE) {
					LANGUAGE=bundlemap.get(lang);
				}
			}
			
		});
	}
	
	public static void initialize() {

		
		initializeBuiltin("english");

		
		
		
		URL langurl = Serialization.PATH_LOADER.findResource("languages/");
		if(langurl!=null) {
			//Custom languages found!
			initializeCustom(langurl);
		}
		
	}

	
}
