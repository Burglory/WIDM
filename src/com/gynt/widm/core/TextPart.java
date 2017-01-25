package com.gynt.widm.core;

import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class TextPart extends ExamPart {

	public String text;
	public long milidelay;

	@Override
	public JSONObject serialize() {
		return Serialization.newJSON(this).put("text", text).put("delay", milidelay);
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		text = j.getString("text");
		milidelay = j.getLong("delay");
		return this;
	}

}
