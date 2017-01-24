package com.gynt.widm.core;

import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public abstract class QuestionPart extends ExamPart {

	public String question;

	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		result.put("question", question);
		return result;
	}

}
