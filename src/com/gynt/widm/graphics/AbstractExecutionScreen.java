package com.gynt.widm.graphics;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractExecutionScreen extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -876397403793390642L;
	public static BufferedImage cursorImg;
	public static Cursor blankCursor;
	
	static {
		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
	}
	
	protected final int dwidth;
	protected final int dheight;
	protected Image image;
	private JLabel contentPane;

	public AbstractExecutionScreen(int width, int height) throws IOException {
		this.dwidth = width;
		this.dheight = height;
		build();
		contentPane = new JLabel(new ImageIcon(image));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setCursor(blankCursor);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE) AbstractExecutionScreen.this.dispose();
			}
		});
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
	}

	public abstract void build() throws IOException;

}
