package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class ExamData implements JSerializable {

	public Participant taker;
	public ArrayList<ExamAnswer> answers;

	@Override
	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		result.put("taker", taker.serialize());
		JSONArray janswers = new JSONArray();
		for(ExamAnswer e : answers) {
			janswers.put(e.serialize());
		}
		result.put("answers", janswers);
		return result;
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		taker = (Participant) Serialization.newObject(j.getJSONObject("taker"));
		for(Object o : j.getJSONArray("answers")) {
			answers.add(new ExamAnswer().deserialize((JSONObject) o));
		}
		return this;
	}

}
