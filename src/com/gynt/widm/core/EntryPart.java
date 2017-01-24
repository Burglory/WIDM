package com.gynt.widm.core;

import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class EntryPart extends QuestionPart {

	@Override
	public JSONObject serialize() {
		return Serialization.forObject(this, super.serialize());
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		return this;
	}

}
