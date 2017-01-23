package com.gynt.widm.graphics.nl.version2014;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.gynt.widm.graphics.AbstractExecutionScreen;

public class ExecutionScreen extends AbstractExecutionScreen {

	/**
	 *
	 */
	private static final long serialVersionUID = -3210312709327332531L;

	public ExecutionScreen(int width, int height) {
		super(width, height);
	}

	public void build() throws IOException {
		Image original = ImageIO.read(getClass().getResourceAsStream("/resources/images/g14.png"));
		BufferedImage i = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = i.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(original, 0, 0, dwidth, dheight, null);
		g.dispose();
		image = i;
	}

	public static void main(String[] args) throws IOException {
		// Test

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1920, 1080);

		ExecutionScreen e = new ExecutionScreen(1920, 1080);
		e.build();

		frame.setContentPane(e);
		frame.setVisible(true);
	}

}
