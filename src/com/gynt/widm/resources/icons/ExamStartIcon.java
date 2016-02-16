package com.gynt.widm.resources.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ExamStartIcon {

	private static int width = 24;
	private static int height = 24;
//	private static double scale = 1.0;
//	private static Image examimg = new ImageIcon(
//			ExamStartIcon.class.getResource("/com/gynt/widm/resources/icons/exam.png")).getImage();
//	private static Image playimg = new ImageIcon(
//			ExamStartIcon.class.getResource("/com/gynt/widm/resources/icons/play.png")).getImage();
	
	public static Image getImage() {
		BufferedImage finalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = finalImg.createGraphics();

		g2.setColor(new Color(38, 150, 33));
		g2.fillPolygon(new Polygon(new int[]{0, width-1, 0}, new int[]{0, (height/2)-1, height-1}, 3));
		
		g2.dispose();
		
		return finalImg;
	}
	
	//temp.drawImage(playimg, 0, 0, width, height, null);
//	for (int y = 0; y < image.getHeight(); y++) {
//		for (int x = 0; x < image.getWidth(); x++) {
//			if(image.getRGB(x, y)==Color.BLACK.getRGB()) {
//				image.setRGB(x, y, new Color(38, 150, 33).getRGB());
//			}
//		}
//	}

	// private static Image getScaledImage(Image srcImg, int w, int h){
	// BufferedImage resizedImg = new BufferedImage(w, h,
	// BufferedImage.TYPE_INT_ARGB);
	// Graphics2D g2 = resizedImg.createGraphics();
	//
	// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	// g2.drawImage(srcImg, 0, 0, w, h, null);
	// g2.dispose();
	//
	// return resizedImg;
	// }

}
