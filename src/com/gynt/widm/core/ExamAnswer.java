package com.gynt.widm.core;

import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class ExamAnswer implements JSerializable {

	public int index = -1;
	public String answer = "";

	@Override
	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		result.put("index", index);
		result.put("answer", answer);
		return result;
	}

	@Override
	public ExamAnswer deserialize(JSONObject j) {
		index = j.getInt("index");
		answer = j.getString("answer");
		return this;
	}

}
