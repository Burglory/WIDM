package com.gynt.widm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import org.w3c.dom.views.AbstractView;

import com.sun.org.apache.xml.internal.utils.StringComparable;

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
	
	public void sort() {
		this.players.sort(new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				return o1.getProperty("name").toString().compareTo(o2.getProperty("name").toString());
			}
			
		});
		this.fireContentsChanged(this, 0, this.players.size()-1);
	}
	
	public void addPlayer(Player p) {
		this.players.add(p);
		this.fireIntervalAdded(this, this.players.size()-1, this.players.size()-1);
	}
	
	public void removeAllPlayers() {
		int size = this.players.size();
		this.players.clear();
		this.fireIntervalRemoved(this, 0, size-1);
	}
	
	public void removePlayer(Player p) {
		int index = this.players.indexOf(p);
		this.players.remove(index);
		this.fireIntervalRemoved(this, index, index);
	}
	
	public void removePlayer(int index) {
		this.players.remove(index);
		this.fireIntervalRemoved(this, index, index);
	}
	
	public void addPlayers(List<Player> players) {
		for(Player p : players) {
			this.addPlayer(p);
		}
		this.sort();
	}
	
	public List<Player> grabPlayers(int[] indexes) {
		List<Player> result = new ArrayList<Player>();
		List<Integer> list = new ArrayList<Integer>();
		for (int index : indexes) {
			list.add(index);
		}
		Collections.reverse(list);
		for (int i : list) {
			result.add(players.get(i));
			this.removePlayer(i);
		}
		this.sort();
		return result;
	}

	@Override
	public int getSize() {
		if(this.players==null) return 0;
		return this.players.size();
	}

	@Override
	public String getElementAt(int index) {
		if(this.players==null) return "";
		return (String) this.players.get(index).getProperty("name");
	}
	
}
