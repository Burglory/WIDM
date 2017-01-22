package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONObject;

public class ChoicePart extends QuestionPart {

	public static class Choice {
		public ArrayList<Participant> participants;
		public String text;
	}

	public ArrayList<Choice> choices;

	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub
		return null;
	}

}
