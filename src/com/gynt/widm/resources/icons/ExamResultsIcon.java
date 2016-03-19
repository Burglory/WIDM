package com.gynt.widm.resources.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ExamResultsIcon {

	private static int width = 24;
	private static int height = 24;

	public static Image getImage() {
		BufferedImage finalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImg.createGraphics();

		g2.setColor(new Color(38, 150, 33));
		
		g2.fillRect(1, 0+(height/3)*1, 6, (height/3)*2);
		g2.fillRect(9, 0, 6, height);
		g2.fillRect(17, 0+(height/3)*2, 6, (height/3)*1);
		
		g2.dispose();
		
		return finalImg;
	}
	
}
