package com.gynt.widm.core;

import com.gynt.widm.core.util.IDProvider;

public class Participant {

	public static enum Type {
		MOLE(0x1), PLAYER(0x2);

		public final int value;

		private Type(int val) {
			value = val;
		}
	}

	public final String id;
	public final Type type;
	public String name;
	public String[] details;

	private Participant(Type t, String i) {
		type = t;
		id = i;
	}

	public static final Participant newMole() {
		return new Participant(Type.MOLE, IDProvider.provide());
	}

	public static final Participant newPlayer() {
		return new Participant(Type.PLAYER, IDProvider.provide());
	}

}
