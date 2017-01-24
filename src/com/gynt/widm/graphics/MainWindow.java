package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.gynt.widm.resources.RBLoader;

public class MainWindow extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 3557878564872334551L;
	private JPanel contentPane;

	private static void setup() {
		setLookAndFeel();
		RBLoader.load();
	}

	private static void setLookAndFeel() {
		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
			if (lafi.getName().equals("Windows")) {
				try {
					UIManager.setLookAndFeel(lafi.getClassName());
					return;
				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
			if (lafi.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(lafi.getClassName());
					return;
				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void run(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setup();
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 546);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New game");
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

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmSettings = new JMenuItem("Settings...");
		mnEdit.add(mntmSettings);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmStatistics = new JMenuItem("Statistics...");
		mnView.add(mntmStatistics);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmAboutWidm = new JMenuItem("About WIDM");
		mnAbout.add(mntmAboutWidm);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel welcomePanel = new JPanel();
		tabbedPane.addTab("Welcome", null, welcomePanel, null);
		welcomePanel.setLayout(new BorderLayout(0, 0));

		RoundScreen round1Panel = new RoundScreen();
		tabbedPane.addTab("Round 1", null, round1Panel, null);

		PreferencesScreen preferencesPanel = new PreferencesScreen();
		tabbedPane.addTab("Preferences", null, preferencesPanel, null);

	}

	public static void main(String[] args) {
		//Debug
		MainWindow.setup();
		MainWindow a = new MainWindow();

		a.setVisible(true);
	}

}
