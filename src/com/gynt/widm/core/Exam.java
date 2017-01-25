package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class Exam implements JSerializable {

	public ArrayList<ExamPart> parts = new ArrayList<>();
	public String name = "";

	@Override
	public JSONObject serialize() {
		JSONObject jresult = Serialization.newJSON(this);
		JSONArray jparts = new JSONArray();
		for (ExamPart e : parts) {
			jparts.put(e.serialize());
		}
		jresult.put("parts", jparts);
		jresult.put("name", name);
		return jresult;
	}

	@Override
	public Exam deserialize(JSONObject j) {
		for (Object e : j.getJSONArray("parts")) {
			parts.add((ExamPart) ((JSerializable) Serialization.newObject((JSONObject) e)).deserialize((JSONObject) e));
		}
		name = j.getString("name");
		return this;
	}

}
