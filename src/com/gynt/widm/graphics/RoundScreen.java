package com.gynt.widm.graphics;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RoundScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -7549616038078869133L;

	public RoundScreen() {
		this.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Participants", null, panel, null);

		ParticipantScreen panel_1 = new ParticipantScreen();
		tabbedPane.addTab("Exam", null, panel_1, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Results", null, panel_3, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Execution", null, panel_2, null);
	}

}
