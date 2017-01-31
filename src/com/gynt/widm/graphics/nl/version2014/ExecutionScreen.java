package com.gynt.widm.graphics.nl.version2014;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gynt.widm.graphics.AbstractBackgroundScreen;
import com.gynt.widm.graphics.FullscreenFrame;

public class ExecutionScreen extends AbstractBackgroundScreen {

	/**
	 *
	 */
	private static final long serialVersionUID = -3210312709327332531L;

	public ExecutionScreen(int width, int height) throws IOException {
		super(width, height);
	}

	@Override
	public Image build() throws IOException {
		Image original = ImageIO.read(getClass().getResourceAsStream("/resources/images/g14.png"));
		BufferedImage i = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = i.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(original, 0, 0, dwidth, dheight, null);
		g.dispose();
		return i;
	}

	public static void main(String[] args) throws IOException {
		// Test
		FullscreenFrame ff = new FullscreenFrame();
		ExecutionScreen e = new ExecutionScreen(1024, 768);

		ff.setContentPane(e);
		ff.setVisible(true);
	}

}
