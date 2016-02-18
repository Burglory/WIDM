package com.gynt.widm.core;

import java.util.List;

import javax.swing.DefaultListModel;

public class PlayerListModel<Player> extends DefaultListModel<Player> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Player> players;

	public PlayerListModel(List<Player> players) {
		this.players = players;
		
	}
	
	@Override
	public Player getElementAt(int index) {
		return this.players.get(index);
	}
	
	@Override
	public int size() {
		return this.players.size();
	}
	
	
	
}
