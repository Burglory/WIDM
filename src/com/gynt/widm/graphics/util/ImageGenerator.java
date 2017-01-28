package com.gynt.widm.graphics.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageGenerator {

	private static BufferedImage i;
	private static BufferedImage i2;

	static{
		try {
			i = ImageIO.read(new File("X:\\My Documents\\Projects\\widm\\fingerprint.jpg"));
			i2 = ImageIO.read(new File("X:\\My Documents\\Projects\\widm\\de_mol.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Image createExamBackground(int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		  Graphics2D tGraphics2D = bi.createGraphics(); //create a graphics object to paint to
		  //tGraphics2D.setBackground( Color.BLACK );
		  //tGraphics2D.setPaint( Color.WHITE );
		  //tGraphics2D.fillRect( 0, 0, width, height);
		  tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  tGraphics2D.drawImage(i, 0, 0, width, height, null ); //draw the image scaled
		  tGraphics2D.drawImage(i2, 0, 0, width, height, null ); //draw the image scaled
		return bi;
	}
	
}
