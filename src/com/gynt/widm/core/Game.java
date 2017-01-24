package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.core.util.Serialization;
import com.gynt.widm.io.GameFileContext;

public class Game implements JSerializable {

	public ArrayList<Round> rounds = new ArrayList<>();
	public GameFileContext fileinterface;

	@Override
	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		JSONArray jrounds = new JSONArray();
		for(Round r : rounds) {
			jrounds.put(r.serialize());
		}
		result.put("rounds", jrounds);
		return result;
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		for(Object o : j.getJSONArray("rounds")) {
			rounds.add((Round) new Round().deserialize((JSONObject) o));
		}
		return null;
	}

}
