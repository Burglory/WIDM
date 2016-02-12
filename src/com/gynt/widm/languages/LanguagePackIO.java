package com.gynt.widm.languages;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.gynt.widm.GUI;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;

public class LanguagePackIO {

//	public static LanguagePack loadDefault() {
//		try {
//			URL url = GUI.class.getClassLoader().getResource("com/gynt/widm/languages/english.lang");
//			System.out.println(url);
//			URI uri = url.toURI();
//			Path path = Paths.get(uri);
//			return load(path);
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LanguagePack result = new LanguagePack();
//		return result;
//	}
//	
//	public static LanguagePack load(Path path) {
//		LanguagePack result = new LanguagePack();
//		List<String> lines;
//		try {
//			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//			for(String line : lines) {
//				String[] lineparts = line.split("=", 2);
//				result.set(lineparts[0], lineparts[1]);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
	
}
