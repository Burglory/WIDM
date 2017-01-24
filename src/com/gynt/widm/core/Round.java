package com.gynt.widm.core;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.core.util.Serialization;

public class Round implements JSerializable {

	public AbstractTableModel PARTICIPANTS;

	public Exam exam;
	public ArrayList<Participant> participants = new ArrayList<Participant>();
	public ArrayList<ExamData> data = new ArrayList<>();

	public Round buildModels() {
		if (PARTICIPANTS == null) {
			PARTICIPANTS = new AbstractTableModel() {

				/**
				 *
				 */
				private static final long serialVersionUID = 1L;
				private final boolean[] editable = new boolean[] { true, true, false, true };
				private final Class<?>[] columnTypes = new Class[] { String.class, String.class, String.class,
						Participant.Type.class };
				private final String[] columnNames = new String[] { "Name", "Password", "ID", "Mole" };

				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}

				@Override
				public Object getValueAt(int row, int col) {
					switch (columnNames[col]) {
					case "Name":
						return participants.get(row).name;
					case "Password":
						return participants.get(row).password;
					case "ID":
						return participants.get(row).id;
					case "Mole":
						return participants.get(row).type;
					default:
						throw new RuntimeException();
					}
				}

				@Override
				public int getRowCount() {
					return participants.size();
				}

				@Override
				public int getColumnCount() {
					return columnNames.length;
				}

				@Override
				public boolean isCellEditable(int row, int col) {
					return editable[col];
				}

				@Override
				public void setValueAt(Object value, int row, int col) {
					switch (columnNames[col]) {
					case "Name":
						participants.get(row).name = (String) value;
						break;
					case "Password":
						participants.get(row).password = (String) value;
						break;
					case "ID":
						// participants.get(row).id = (String) value;
						break;
					case "Mole":
						participants.get(row).type = (Participant.Type) value;
						break;
					}
					fireTableCellUpdated(row, col);
				}

				@Override
				public String getColumnName(int col) {
					return columnNames[col].toString();
				}
			};
		}
		return this;
	}

	@Override
	public JSONObject serialize() {
		JSONObject result = Serialization.newJSON(this);
		result.put("exam", exam.serialize());
		JSONArray jparticipants = new JSONArray();
		for (Participant p : participants) {
			jparticipants.put(p.serialize());
		}
		result.put("participants", jparticipants);
		JSONArray jexamdata = new JSONArray();
		for (ExamData e : data) {
			jexamdata.put(e.serialize());
		}
		result.put("data", jexamdata);
		return result;
	}

	@Override
	public JSerializable deserialize(JSONObject j) {
		exam = new Exam().deserialize(j.getJSONObject("exam"));
		for (Object o : j.getJSONArray("participants")) {
			participants.add((Participant) Serialization.newObject((JSONObject) o));
		}
		for (Object o : j.getJSONArray("data")) {
			data.add((ExamData) Serialization.newObject((JSONObject) o));
		}
		return this;
	}

}
