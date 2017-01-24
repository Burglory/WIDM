package com.gynt.widm.core.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.gynt.widm.core.JSerializable;

public class Serialization {

	public static <T> JSONObject newJSON(T t) {
		return new JSONObject().put("type", t.getClass().getName());
	}

	public static <T> JSONObject forObject(T t, JSONObject j) {
		return j.put("type", t.getClass().getName());
	}

	public static Object newObject(JSONObject j) {
		if(!j.has("type")) throw new RuntimeException();
		Object result;
		try {
			result = Serialization.class.getClassLoader().loadClass("com.gynt.widm.core."+j.getString("type")).newInstance();
			if(result instanceof JSerializable) {
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

}
