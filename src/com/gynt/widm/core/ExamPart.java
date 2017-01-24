package com.gynt.widm.core;

import org.json.JSONObject;

public abstract class ExamPart implements JSerializable {

	public abstract JSONObject serialize();

}
