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
		
		//JLabel lblNewLabel = new JLabel("New label");
		image = ImageGenerator.createExamBackground(200, 200);
		//add(lblNewLabel);
		
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.drawImage(image, 0, 0, null);
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
		System.out.println("ok");
		FullscreenFrame ff = new FullscreenFrame();
		ExamLoginScreen els = new ExamLoginScreen();
		ff.setPanel(els);
		ff.setVisible(true);
	}
	
}
