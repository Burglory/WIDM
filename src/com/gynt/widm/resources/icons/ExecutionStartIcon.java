package com.gynt.widm.resources.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ExecutionStartIcon {

	private static int width = 24;
	private static int height = 24;
//	private static double scale = 1.0;
//	private static Image playimg = new ImageIcon(
//			ExecutionStartIcon.class.getResource("/com/gynt/widm/resources/icons/play.png")).getImage();


	
	public static Image getImage() {
		BufferedImage finalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImg.createGraphics();

		g2.setColor(new Color(170, 27, 0));
		g2.fillPolygon(new Polygon(new int[]{0, width-1, 0}, new int[]{0, (height/2)-1, height-1}, 3));
		
		g2.dispose();
		
		return finalImg;
	}

}
