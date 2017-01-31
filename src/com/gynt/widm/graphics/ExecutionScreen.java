package com.gynt.widm.graphics;

import javax.swing.JPanel;

import com.gynt.widm.core.Preferences;
import com.gynt.widm.core.Preferences.PreferenceItem;
import com.gynt.widm.core.Preferences.PreferenceSub;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class ExecutionScreen extends JPanel {
	public ExecutionScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnNextRound = new JButton("Next round...");
		toolBar.add(btnNextRound);
	}

	private static PreferenceItem oldstyle;
	private static PreferenceItem newstyle;
	private static PreferenceItem music;

	static {
		PreferenceSub s = Preferences.ROOT.registerDir("Execution").registerSub("Visual", "Visual style");
		oldstyle = s.registerItem("1999", "Old style", Preferences.Radio.class, Boolean.FALSE);
		newstyle = s.registerItem("2014", "New style", Preferences.Radio.class, Boolean.TRUE);
		s = Preferences.ROOT.registerDir("Execution").registerSub("Music", "Music style");
		music = s.registerItem("music", "Play music", Boolean.class, Boolean.FALSE);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1125561711058186428L;

}
