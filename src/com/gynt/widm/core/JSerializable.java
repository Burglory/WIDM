package com.gynt.widm.core;

import org.json.JSONObject;

public interface JSerializable {

	JSONObject serialize();
	JSerializable deserialize(JSONObject j);

}
