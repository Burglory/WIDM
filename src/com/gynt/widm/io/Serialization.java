package com.gynt.widm.io;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import org.json.JSONException;
import org.json.JSONObject;

import com.gynt.widm.core.JSerializable;
import com.gynt.widm.core.util.ExceptionDisplay;

public class Serialization {

	public static <T> JSONObject newJSON(T t) {
		return new JSONObject().put("type", t.getClass().getSimpleName());
	}

	public static <T> JSONObject forObject(T t, JSONObject j) {
		return j.put("type", t.getClass().getSimpleName());
	}

	public static Object newObject(JSONObject j) {
		if (!j.has("type"))
			throw new RuntimeException();
		Object result;
		try {
			result = Serialization.class.getClassLoader().loadClass("com.gynt.widm.core." + j.getString("type"))
					.newInstance();
			if (result instanceof JSerializable) {
				((JSerializable) result).deserialize(j);
			}
			return result;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ClassLoader LOADER = ClassLoader.getSystemClassLoader();
	public static URLClassLoader PATH_LOADER = null;

	static {
		try {
			PATH_LOADER = new URLClassLoader(
					new URL[] { ClassLoader.getSystemClassLoader().getResource(".").toURI().toURL() });
		} catch (MalformedURLException e) {
			ExceptionDisplay.raiseNewExceptionDisplay(e, "Loading of the file loader failed.");
			System.exit(1);
		} catch (URISyntaxException e) {
			ExceptionDisplay.raiseNewExceptionDisplay(e, "Loading of the file loader failed.");
			System.exit(1);
		}
	}
}
