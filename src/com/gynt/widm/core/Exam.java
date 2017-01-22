package com.gynt.widm.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exam {

	private static String TYPEKEY = "type";
	private static String PARTSKEY = "parts";
	private static String TYPEVALUE = "exam";

	public ArrayList<ExamPart> parts;
	public String name;

	public JSONObject serialize() {
		JSONObject jresult = new JSONObject();
		JSONArray jparts = new JSONArray();
		for (ExamPart e : parts) {
			jparts.put(e.serialize());
		}
		jresult.put(TYPEKEY, TYPEVALUE);
		jresult.put(PARTSKEY, jparts);
		return jresult;
	}

}
