package com.gynt.widm;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.gynt.widm.languages.LanguagePack;
import com.gynt.widm.languages.LanguagePackIO;

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

public class GUI {
	
	private JFrame frmWidm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frmWidm.getContentPane().add(new PlayersPanel(), BorderLayout.CENTER);
		
		JButton btnStartExam = new JButton("Start exam");
		toolBar.add(btnStartExam);
		
		JButton btnEditExam = new JButton("Edit exam");
		toolBar.add(btnEditExam);
		
		JButton btnPlayers = new JButton("Players");
		toolBar.add(btnPlayers);
		
		JButton btnStartExecution = new JButton("Start execution");
		toolBar.add(btnStartExecution);
		
		JButton btnEditExecution = new JButton("Edit execution");
		toolBar.add(btnEditExecution);
		
		JButton btnProgressToNew = new JButton("Progress to new episode");
		toolBar.add(btnProgressToNew);
		
		JButton btnPreviousEpisode = new JButton("Previous episode");
		toolBar.add(btnPreviousEpisode);
		
		JButton btnNextEpisode = new JButton("Next episode");
		toolBar.add(btnNextEpisode);
	}

}
