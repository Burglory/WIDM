package com.gynt.widm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.gynt.widm.core.Player;
import com.gynt.widm.languages.LanguagePack;
import com.gynt.widm.languages.LanguagePackIO;
import com.gynt.widm.resources.icons.ExamEditIcon;
import com.gynt.widm.resources.icons.ExamResultsIcon;
import com.gynt.widm.resources.icons.ExamStartIcon;
import com.gynt.widm.resources.icons.ExecutionEditIcon;
import com.gynt.widm.resources.icons.ExecutionStartIcon;
import com.gynt.widm.resources.icons.NextRoundIcon;
import com.gynt.widm.resources.icons.PreviousRoundIcon;
import com.gynt.widm.resources.icons.ProgressNextRoundIcon;
import com.gynt.widm.resources.icons.SettingsIcon;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {
	
	public static boolean DebugMode = true;
	private JFrame frmWidm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmWidm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWidm = new JFrame();
		frmWidm.setTitle("WIDM");
		frmWidm.setBounds(0, 0, 1024, 768);
		frmWidm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmWidm.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewGameFrame ngf = new NewGameFrame(new ArrayList<Player>());
				ngf.setVisible(true);
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as...");
		mnFile.add(mntmSaveAs);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frmWidm.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar.addSeparator();
		
		JButton btnEditexam = new JButton("");
		btnEditexam.setToolTipText("Edit the exam.");
		btnEditexam.setIcon(new ImageIcon(ExamEditIcon.getImage()));
		btnEditexam.setFocusPainted(false);
		toolBar.add(btnEditexam);
		
		JButton btnStartExam = new JButton("");
		btnStartExam.setToolTipText("Run the exam for this round.");
		btnStartExam.setIcon(new ImageIcon(ExamStartIcon.getImage()));
		btnStartExam.setFocusPainted(false);
		toolBar.add(btnStartExam);
		
		JButton btnResultsExam = new JButton("");
		btnResultsExam.setToolTipText("View the results for the exam.");
		btnResultsExam.setIcon(new ImageIcon(ExamResultsIcon.getImage()));
		btnResultsExam.setFocusPainted(false);
		toolBar.add(btnResultsExam);
		toolBar.addSeparator();
		
		JButton btnEditexecution = new JButton("");
		btnEditexecution.setToolTipText("Edit the execution.");
		btnEditexecution.setIcon(new ImageIcon(ExecutionEditIcon.getImage()));
		btnEditexecution.setFocusPainted(false);
		toolBar.add(btnEditexecution);
		
		JButton btnStartExecution = new JButton("");
		btnStartExecution.setToolTipText("Run the execution for this round.");
		btnStartExecution.setIcon(new ImageIcon(ExecutionStartIcon.getImage()));
		btnStartExecution.setFocusPainted(false);
		toolBar.add(btnStartExecution);
		
		JButton btnProgressToNew = new JButton("");
		btnProgressToNew.setToolTipText("Progress to next round and leave out executed player(s).");
		btnProgressToNew.setIcon(new ImageIcon(ProgressNextRoundIcon.getImage()));
		btnProgressToNew.setFocusPainted(false);
		toolBar.add(btnProgressToNew);
		toolBar.addSeparator();
		
		JButton btnPreviousEpisode = new JButton("");
		btnPreviousEpisode.setToolTipText("Previous round.");
		btnPreviousEpisode.setIcon(new ImageIcon(PreviousRoundIcon.getImage()));
		btnPreviousEpisode.setFocusPainted(false);
		toolBar.add(btnPreviousEpisode);
		
		JButton btnNextEpisode = new JButton("");
		btnNextEpisode.setToolTipText("Next round.");
		btnNextEpisode.setIcon(new ImageIcon(NextRoundIcon.getImage()));
		btnNextEpisode.setFocusPainted(false);
		toolBar.add(btnNextEpisode);
		toolBar.addSeparator();
		
		JButton btnSettings = new JButton("");
		btnSettings.setToolTipText("Access the settings.");
		btnSettings.setIcon(new ImageIcon(SettingsIcon.getImage()));
		btnSettings.setFocusPainted(false);
		toolBar.add(btnSettings);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmWidm.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Players", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCurrentRound = new JLabel("Current round:");
		GridBagConstraints gbc_lblCurrentRound = new GridBagConstraints();
		gbc_lblCurrentRound.anchor = GridBagConstraints.NORTH;
		gbc_lblCurrentRound.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentRound.gridx = 0;
		gbc_lblCurrentRound.gridy = 0;
		panel.add(lblCurrentRound, gbc_lblCurrentRound);
		
		JLabel label = new JLabel("0");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCurrentPlayers = new JLabel("Players in round:");
		GridBagConstraints gbc_lblCurrentPlayers = new GridBagConstraints();
		gbc_lblCurrentPlayers.anchor = GridBagConstraints.NORTH;
		gbc_lblCurrentPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentPlayers.gridx = 0;
		gbc_lblCurrentPlayers.gridy = 1;
		panel.add(lblCurrentPlayers, gbc_lblCurrentPlayers);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.weightx = 0.5;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
		
		JLabel lblNewLabel = new JLabel("Players in game:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JList list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.weightx = 0.5;
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 1;
		gbc_list_1.gridy = 2;
		panel.add(list_1, gbc_list_1);
	}

}
