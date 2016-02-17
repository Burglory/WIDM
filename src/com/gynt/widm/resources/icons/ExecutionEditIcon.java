package com.gynt.widm.resources.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ExecutionEditIcon {

	private static int width = 24;
	private static int height = 24;

	public static Image getImage() {
		BufferedImage finalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImg.createGraphics();

		g2.setColor(new Color(170, 27, 0));
		g2.fillRect(0, 1, width, 6);
		g2.fillRect(0, 9, width, 6);
		g2.fillRect(0, 17, width, 6);
		
		g2.dispose();
		
		return finalImg;
	}
	
}
