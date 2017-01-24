package com.gynt.widm.graphics;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.gynt.widm.core.Round;

public class RoundScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -7549616038078869133L;
	private Round round;
	private JTabbedPane tabbedPane;

	public RoundScreen() {
		this.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.add(tabbedPane, BorderLayout.CENTER);

		ParticipantScreen panel = new ParticipantScreen();
		tabbedPane.addTab("Participants", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Exam", null, panel_1 , null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Results", null, panel_3, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Execution", null, panel_2, null);
	}

	public RoundScreen(Round r) {
		this();
		tabbedPane.removeAll();

		round = r;
		ParticipantScreen panel = new ParticipantScreen(round);
		tabbedPane.addTab("Participants", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Exam", null, panel_1 , null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Results", null, panel_3, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Execution", null, panel_2, null);
	}

}
