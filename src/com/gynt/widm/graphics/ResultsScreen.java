package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

public class ResultsScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -3927897915629073976L;
	private boolean initial;

	public ResultsScreen() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton btnResetPlayer = new JButton("Reset player...");
		toolBar.add(btnResetPlayer);

		JSplitPane questionsplitPane = new JSplitPane();
		add(questionsplitPane, BorderLayout.CENTER);
		questionsplitPane.setResizeWeight(0.50);

		JPanel panel_4 = new JPanel();
		questionsplitPane.setRightComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JSplitPane participantsplitPane = new JSplitPane();
		panel_4.add(participantsplitPane);
		participantsplitPane.setResizeWeight(0.50);

		JPanel panel_2 = new JPanel();
		participantsplitPane.setLeftComponent(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Participants", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(59, 59, 59)));

		JList participantList = new JList();
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(participantList);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Answers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		participantsplitPane.setRightComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JList answerList = new JList();
		panel_3.add(answerList);

		JPanel panel_1 = new JPanel();
		questionsplitPane.setLeftComponent(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Questions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new BorderLayout(0, 0));

		JList questionList = new JList();
		panel_1.add(questionList);

		initial = false;
		addComponentListener(new ComponentListener() {

			double pdiv = 0.50;
			double qdiv = 0.25;

			@Override
			public void componentShown(ComponentEvent arg0) {
				// participantsplitPane.setDividerLocation(pdiv);
				// questionsplitPane.setDividerLocation(qdiv);

			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// participantsplitPane.setDividerLocation(pdiv);
				// questionsplitPane.setDividerLocation(qdiv);
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
