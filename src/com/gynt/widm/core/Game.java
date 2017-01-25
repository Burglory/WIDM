package com.gynt.widm.core;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.GameFileContext;
import com.gynt.widm.io.Serialization;

public class Game implements JSerializable {

	public ArrayList<Round> rounds = new ArrayList<>();
	public GameFileContext fileinterface;

	public void save() throws IOException {
		fileinterface.store(serialize().toString().getBytes(Charset.forName("UTF-8")), "gamefile.json");
	}

	public void load() {
		deserialize(new JSONObject(new String(fileinterface.retrieve("gamefile.json"), Charset.forName("UTF-8"))));
	}

	@Override
	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		JSONArray jrounds = new JSONArray();
		for (Round r : rounds) {
			jrounds.put(r.serialize());
		}
		result.put("rounds", jrounds);
		return result;
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		for (Object o : j.getJSONArray("rounds")) {
			rounds.add((Round) new Round().deserialize((JSONObject) o));
		}
		return null;
	}

}
