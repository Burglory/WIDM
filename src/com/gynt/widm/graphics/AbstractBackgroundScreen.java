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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractBackgroundScreen extends JPanel {

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

	public AbstractBackgroundScreen(int width, int height) throws IOException {
		this.dwidth = width;
		this.dheight = height;
		image = build();
		if(image==null) throw new RuntimeException();
		setLayout(new BorderLayout(0, 0));
		setCursor(blankCursor);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public abstract Image build() throws IOException;

}
