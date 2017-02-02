package com.gynt.widm.graphics;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTree;

import com.gynt.easysettings.Settings;

public class PreferencesPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -1551845699358510952L;
	private JPanel prefPanel;
	private JTree prefTree;
	private com.gynt.easysettings.SettingsPanel panel;

	/**
	 * Create the panel.
	 */
	private PreferencesPanel() {
		setLayout(new BorderLayout(0, 0));
	}
	
	public PreferencesPanel(Settings s) {
		this();
		panel = new com.gynt.easysettings.SettingsPanel(s);
		//panel.render();
		add(panel, BorderLayout.CENTER);
	}

}
