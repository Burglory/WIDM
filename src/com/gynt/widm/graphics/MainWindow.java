package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import com.gynt.widm.core.Game;
import com.gynt.widm.core.Round;
import com.gynt.widm.core.util.ExceptionDisplay;
import com.gynt.widm.resources.RBLoader;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 3557878564872334551L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private Game game;

	private static void setup() {
		setLookAndFeel();
		RBLoader.load();
	}

	private static void setLookAndFeel() {
//		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
//			if (lafi.getName().equals("Windows")) {
//				try {
//					UIManager.setLookAndFeel(lafi.getClassName());
//					return;
//				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
//						| IllegalAccessException e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
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

	private void loadGame() {

	}

	private void saveGameAs() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, URISyntaxException {
		JFileChooser jfc = new JFileChooser();
		jfc.showSaveDialog(this);
		jfc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Game file";
			}

			@Override
			public boolean accept(File f) {
				return true;
			}
		});;
		if (jfc.getSelectedFile() != null) {
			game.setFile(jfc.getSelectedFile());
			saveGame();
		}
	}

	private void saveGame() throws IOException, URISyntaxException {
		if(game==null) return;
		if(game.fileinterface!=null) {
			game.save();

		} else {
			try {
				saveGameAs();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 836, 546);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(game!=null) {
					int result = JOptionPane.showConfirmDialog(MainWindow.this, "Do you want to save the game?");
					if (result == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (result == JOptionPane.YES_OPTION) {
						try {
							saveGame();
							MainWindow.this.dispose();
						} catch (IOException e1) {
							ExceptionDisplay.raiseNewExceptionDisplay(e1, "Saving the file failed.");
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (result == JOptionPane.NO_OPTION) {
						MainWindow.this.dispose();
					}
				} else {
					MainWindow.this.dispose();
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New game");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame(new Game());
			}
		});
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmOpen);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
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

		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel welcomePanel = new JPanel();
		tabbedPane.addTab("Welcome", null, welcomePanel, null);
		welcomePanel.setLayout(new BorderLayout(0, 0));

//		RoundScreen round1Panel = new RoundScreen();
//		tabbedPane.addTab("Round 1", null, round1Panel, null);
//
//		PreferencesScreen preferencesPanel = new PreferencesScreen();
//		tabbedPane.addTab("Preferences", null, preferencesPanel, null);

	}

	private void loadGame(Game g) {
		tabbedPane.removeAll();
		game=g;
		if(g.rounds.size()==0) {
			g.rounds.add(new Round());
			RoundScreen round1Panel = new RoundScreen(g.rounds.get(0));
			tabbedPane.addTab("Round 1", null, round1Panel, null);
		} else {
			for(int i = 0; i < g.rounds.size(); i++) {
				tabbedPane.addTab("Round "+(i+1), null, new RoundScreen(g.rounds.get(i)), null);
			}
		}

		PreferencesScreen preferencesPanel = new PreferencesScreen();
		tabbedPane.addTab("Preferences", null, preferencesPanel, null);
	}

	public static void main(String[] args) {
		//Debug
		MainWindow.setup();
		MainWindow a = new MainWindow();
//		a.tabbedPane.remove(1);
//		a.tabbedPane.insertTab("Round 1", null, new RoundScreen(new Round()), null, 1);
		a.setVisible(true);
	}

}