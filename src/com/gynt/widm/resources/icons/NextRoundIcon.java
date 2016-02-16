package com.gynt.widm.resources.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

public class NextRoundIcon {

	
	private static int width=36;
	private static int height=24;
	
	public static Image getImage() {
		
		int width1 = (width/3)*2;
		int startx2 = width-width1;
		
		BufferedImage finalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImg.createGraphics();

		g2.setColor(Color.black);
		g2.fillPolygon(new Polygon(new int[]{0, width1-1, 0}, new int[]{0, (height/2)-1, height-1}, 3));
		g2.fillPolygon(new Polygon(new int[]{startx2, width-1, startx2}, new int[]{0, (height/2)-1, height-1}, 3));
		
		g2.dispose();
		
		return finalImg;
	}
	
}
