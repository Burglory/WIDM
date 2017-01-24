package com.gynt.widm.core;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONString;

import com.gynt.widm.core.util.IDProvider;
import com.gynt.widm.io.Serialization;

public class Participant implements JSerializable {

	public static enum Type {
		MOLE(0x1), PLAYER(0x2);

		public final int value;

		private Type(int val) {
			value = val;
		}

		public static Type lookUp(int i) {
			for(Type t : Type.values()) if(t.value==i) return t;
			return null;
		}
	}

	public String id;
	public Type type;
	public String name;
	public String password;
	public Map<String, Object> details;

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

	@Override
	public JSerializable deserialize(JSONObject j) {
		id=j.getString("id");
		type=Type.lookUp(j.getInt("ptype"));
		name=j.getString("name");
		password=j.getString("password");
		details = j.getJSONObject("details").toMap();
		return this;
	}

}
