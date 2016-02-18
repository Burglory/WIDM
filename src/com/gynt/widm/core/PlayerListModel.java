package com.gynt.widm.core;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import org.w3c.dom.views.AbstractView;

public class PlayerListModel extends DefaultListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Player> players;

	public PlayerListModel(List<Player> players) {
		super();
		this.players = players;
	}

	@Override
	public int getSize() {
		return this.players.size();
	}

	@Override
	public String getElementAt(int index) {
		return (String) this.players.get(index).getProperty("Name");
	}
	
}
