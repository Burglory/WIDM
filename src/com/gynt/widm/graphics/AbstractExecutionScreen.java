package com.gynt.widm.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JPanel;

public abstract class AbstractExecutionScreen extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -876397403793390642L;
	protected final int dwidth;
	protected final int dheight;
	protected Image image;

	public AbstractExecutionScreen(int width, int height) {
		this.dwidth = width;
		this.dheight = height;
		this.setDoubleBuffered(true);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		if (image != null)
			g.drawImage(image, 0, 0, null);
	}

	public abstract void build() throws IOException;

}
