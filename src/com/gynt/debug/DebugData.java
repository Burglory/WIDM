package com.gynt.debug;

import java.util.ArrayList;
import java.util.List;

import com.gynt.widm.core.Player;

public class DebugData {

	public static List<Player> playerdebug;
	static {
		playerdebug = new ArrayList<Player>();
		Player p = new Player("A");
		p.setProperty("Name", "A");
		playerdebug.add(p);
		p = new Player("B");
		p.setProperty("Name", "B");
		playerdebug.add(p);
		p = new Player("C");
		p.setProperty("Name", "C");
		playerdebug.add(p);
		p = new Player("D");
		p.setProperty("Name", "D");
		playerdebug.add(p);
	}
	
}
