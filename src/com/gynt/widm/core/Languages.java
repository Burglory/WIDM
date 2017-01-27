package com.gynt.widm.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.gynt.widm.core.Settings.PreferenceDir;
import com.gynt.widm.core.Settings.PreferenceSub;
import com.gynt.widm.core.Settings.Radio;
import com.gynt.widm.io.Serialization;

public class Languages {
	
	private static PreferenceSub language;

	static {
		language = Settings.ROOT.registerSub("Language","Preferred display language");
		language.registerItem("en", "English", Radio.class, Boolean.TRUE);
		initialize();
	}
	
	private static HashMap<String, ResourceBundle> bundlemap = new HashMap<>();
	
	public static ResourceBundle MAP;

	public static Properties PROPERTIES = new Properties();

	public static void save() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PROPERTIES.store(bos, "");
		Path p = new File(Serialization.LOADER.getResource("language.properties").toString().split("file:/")[1]).toPath();
		save(p, bos.toByteArray());
	}

	private static void save(Path p, byte[] data) throws IOException {
		Files.write(p, data);
	}
	
	public static void initialize() {
		
		URL langurl = Serialization.LOADER.findResource("languages/");
		if(langurl!=null) {
			//Custom languages found!
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
					String lang = file.getName().split("[.]")[0].split("_")[1];
					bundlemap.put(lang, ResourceBundle.getBundle("languages.language", Locale.forLanguageTag(lang), Serialization.LOADER));
					language.registerItem(lang, lang, Radio.class, Boolean.TRUE);
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void load() {
		
//		try {
//			MAP = ResourceBundle.getBundle("language", Locale.getDefault(), Serialization.LOADER);
//		} catch {
//			
//		}
	}

	public static boolean loaded() {
		return PROPERTIES != null;
	}

	
}
