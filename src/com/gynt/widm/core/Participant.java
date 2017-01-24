package com.gynt.widm.core;

import java.util.HashMap;

import org.json.JSONObject;

import com.gynt.widm.core.util.IDProvider;
import com.gynt.widm.core.util.Serialization;

public class Participant {

	public static enum Type {
		MOLE(0x1), PLAYER(0x2);

		public final int value;

		private Type(int val) {
			value = val;
		}
	}

	public final String id;
	public Type type;
	public String name;
	public String password;
	public HashMap<String, String> details;

	private Participant(Type t, String i) {
		type = t;
		id = i;
	}

	public static final Participant newMole() {
		return new Participant(Type.MOLE, IDProvider.provide());
	}

	public static final Participant newPlayer() {
		return new Participant(Type.PLAYER, IDProvider.provide());
	}

	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		result.put("id", id);
		result.put("ptype", type.value);
		result.put("name", name);
		result.put("password", password);
		JSONObject jdetails = new JSONObject(details);
		result.put("details", jdetails);
		return result;
	}

}
