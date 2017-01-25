package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class ChoicePart extends QuestionPart {

	public static class Choice implements JSerializable {
		public ArrayList<Participant> participants = new ArrayList<>();
		public String text = "";

		@Override
		public JSONObject serialize() {
			JSONArray jparticipants = new JSONArray();
			for (Participant p : participants) {
				jparticipants.put(p);
			}
			return Serialization.newJSON(this).put("participants", jparticipants).put("text", text);
		}

		@Override
		public JSerializable deserialize(JSONObject j) {
			for (Object o : j.getJSONArray("participants")) {
				participants.add((Participant) Serialization.newObject((JSONObject) o));
			}
			text = j.getString("text");
			return null;
		}
	}

	public ArrayList<Choice> choices = new ArrayList<>();

	@Override
	public JSONObject serialize() {
		JSONArray jchoices = new JSONArray();
		for (Choice c : choices) {
			jchoices.put(c.serialize());
		}
		return Serialization.forObject(this, super.serialize()).put("choices", jchoices);
	}

	@Override
	public ChoicePart deserialize(JSONObject j) {
		for (Object o : j.getJSONArray("choices")) {
			choices.add((Choice) new Choice().deserialize((JSONObject) o));
		}
		return this;
	}

}
