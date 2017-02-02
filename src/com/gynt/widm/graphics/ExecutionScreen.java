package com.gynt.widm.graphics;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.gynt.easysettings.Settings;
import com.gynt.easysettings.Settings.Item;
import com.gynt.easysettings.Settings.Sub;
import com.gynt.widm.core.Preferences;

public class ExecutionScreen extends JPanel {
	public ExecutionScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnNextRound = new JButton("Next round...");
		toolBar.add(btnNextRound);
	}

	private static Item oldstyle;
	private static Item newstyle;
	private static Item music;

	static {
		Sub s = Preferences.ROOT.registerDir("Execution").registerSub("Visual", "Visual style");
		oldstyle = s.registerItem("1999", "Old style", Settings.Type.RADIO, Boolean.FALSE);
		newstyle = s.registerItem("2014", "New style", Settings.Type.RADIO, Boolean.TRUE);
		s = Preferences.ROOT.registerDir("Execution").registerSub("Music", "Music style");
		music = s.registerItem("music", "Play music", Settings.Type.BOOLEAN, Boolean.FALSE);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1125561711058186428L;

}
