package com.gynt.widm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
