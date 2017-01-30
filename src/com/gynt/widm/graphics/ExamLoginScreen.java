package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gynt.widm.graphics.util.ImageGenerator;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ExamLoginScreen extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3490052390425991902L;
	private Image image;

	/**
	 * Create the frame.
	 */
	public ExamLoginScreen() {
		setBackground(Color.BLACK);
		image = ImageGenerator.createExamBackground(1920/3);
	}
	
	public ExamLoginScreen(int width, int height) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, (1920-image.getWidth(null))/2, (1080-image.getHeight(null))/2, null);
	}

	public static void main(String[] args) {
		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
			if (lafi.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(lafi.getClassName());
				} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		FullscreenFrame ff = new FullscreenFrame();
		ExamLoginScreen els = new ExamLoginScreen();
		ff.setContentPane(els);
		ff.setVisible(true);
	}
	
}
