package com.gynt.widm.resources;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class RBLoader {

	public static ResourceBundle texts;

	public static void load() {
		texts = ResourceBundle.getBundle("resources.Texts", new Locale("en", "us"));
	}

	public static void main(String[] args) {

		ResourceBundle rb = ResourceBundle.getBundle("resources.Texts", new Locale("en", "us"));
		Enumeration<String> as = rb.getKeys();
		while (as.hasMoreElements()) {
			System.out.println(rb.getString(as.nextElement()));
		}
		return;
		// File file = new File("C:\\temp");
		// URL[] urls = {file.toURI().toURL()};
		// ClassLoader loader = new URLClassLoader(urls);
		// ResourceBundle rb = ResourceBundle.getBundle("myResource",
		// Locale.getDefault(), loader);
	}

}
