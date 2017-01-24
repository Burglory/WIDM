package com.gynt.widm.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.GameFileContext;
import com.gynt.widm.io.Serialization;

public class Game implements JSerializable {

	public ArrayList<Round> rounds = new ArrayList<>();
	public GameFileContext fileinterface;

	public void save() throws IOException, URISyntaxException {
		fileinterface.save(serialize().toString().getBytes(Charset.forName("UTF-8")), fileinterface.getRoot().resolve("gamefile.json"));
	}

	public void setFile(File file) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, URISyntaxException {
		fileinterface=GameFileContext.newInterface(file);
	}

	public void load(File file) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, URISyntaxException {
		fileinterface=GameFileContext.newInterface(file);
		deserialize(new JSONObject(new String(fileinterface.load(fileinterface.getRoot().resolve("gamefile.json")), Charset.forName("UTF-8"))));
	}

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
