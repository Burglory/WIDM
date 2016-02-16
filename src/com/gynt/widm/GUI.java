package com.gynt.widm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.gynt.widm.languages.LanguagePack;
import com.gynt.widm.languages.LanguagePackIO;
import com.gynt.widm.resources.icons.ExamStartIcon;
import com.gynt.widm.resources.icons.ExecutionStartIcon;
import com.gynt.widm.resources.icons.NextRoundIcon;
import com.gynt.widm.resources.icons.PreviousRoundIcon;
import com.gynt.widm.resources.icons.ProgressNextRoundIcon;

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

public class GUI {
	
	private JFrame frmWidm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as...");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frmWidm.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnStartExam = new JButton("");
		btnStartExam.setIcon(new ImageIcon(ExamStartIcon.getImage()));
		btnStartExam.setFocusPainted(false);
		toolBar.add(btnStartExam);
		toolBar.addSeparator();
		
		JButton btnStartExecution = new JButton("");
		btnStartExecution.setIcon(new ImageIcon(ExecutionStartIcon.getImage()));
		btnStartExecution.setFocusPainted(false);
		toolBar.add(btnStartExecution);
		
		JButton btnProgressToNew = new JButton("");
		btnProgressToNew.setIcon(new ImageIcon(ProgressNextRoundIcon.getImage()));
		btnProgressToNew.setFocusPainted(false);
		toolBar.add(btnProgressToNew);
		toolBar.addSeparator();
		
		JButton btnPreviousEpisode = new JButton("");
		btnPreviousEpisode.setIcon(new ImageIcon(PreviousRoundIcon.getImage()));
		btnPreviousEpisode.setFocusPainted(false);
		toolBar.add(btnPreviousEpisode);
		
		JButton btnNextEpisode = new JButton("");
		btnNextEpisode.setIcon(new ImageIcon(NextRoundIcon.getImage()));
		btnNextEpisode.setFocusPainted(false);
		toolBar.add(btnNextEpisode);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		frmWidm.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Players", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Exam", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Execution", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Statistics", null, panel_3, null);
	}

}
