package com.gynt.widm.core;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gynt.widm.io.Serialization;

public class Round implements JSerializable {

	public static class RoundTableModel extends AbstractTableModel {


		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private final boolean[] editable = new boolean[] { true, true, false, true };
		private final Class<?>[] columnTypes = new Class[] { String.class, String.class, String.class,
				Participant.Type.class };
		private final String[] columnNames = new String[] { "Name", "Password", "ID", "Type" };
		private Round round;

		public RoundTableModel(Round r) {
			round=r;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (columnNames[col]) {
			case "Name":
				return round.participants.get(row).name;
			case "Password":
				return round.participants.get(row).password;
			case "ID":
				return round.participants.get(row).id;
			case "Type":
				return round.participants.get(row).type;
			default:
				throw new RuntimeException();
			}
		}

		@Override
		public int getRowCount() {
			return round.participants.size();
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
				round.participants.get(row).name = (String) value;
				break;
			case "Password":
				round.participants.get(row).password = (String) value;
				break;
			case "ID":
				// participants.get(row).id = (String) value;
				break;
			case "Type":
				round.participants.get(row).type = (Participant.Type) value;
				break;
			}
			fireTableCellUpdated(row, col);
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col].toString();
		}

		public void addRow(Participant newPlayer) {
			round.participants.add(newPlayer);
			fireTableRowsInserted(round.participants.size()-1, round.participants.size()-1);
		}

		public void deleteRow(Participant newPlayer) {
			int index = round.participants.indexOf(newPlayer);
			round.participants.remove(newPlayer);
			fireTableRowsDeleted(index, index);
		}

		public void deleteRow(int index) {
			round.participants.remove(index);
			fireTableRowsDeleted(index, index);
		}

	}

	public RoundTableModel PARTICIPANTS;

	public Exam exam = new Exam();
	public ArrayList<Participant> participants = new ArrayList<Participant>();
	public ArrayList<ExamData> data = new ArrayList<>();

	public Round buildModels() {
		if (PARTICIPANTS == null) {
			PARTICIPANTS = new RoundTableModel(this);
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
