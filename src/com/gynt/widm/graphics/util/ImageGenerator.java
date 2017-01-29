package com.gynt.widm.graphics.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.gynt.widm.core.Settings;
import com.gynt.widm.core.Settings.PreferenceItem;

public class ImageGenerator {

	private static BufferedImage i;
	private static BufferedImage i2;
	private static PreferenceItem brightness;

	static{
		try {
			i = ImageIO.read(new File("X:\\My Documents\\Projects\\widm\\fingerprint.jpg"));
			i2 = ImageIO.read(new File("X:\\My Documents\\Projects\\widm\\de_mol.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brightness = Settings.ROOT.registerDir("Exam").registerSub("Background", "Background").registerItem("Brightness", "Brightness", Float.class, Float.valueOf(0.80f));
	}
	
	public static void wakey() {
		
	}
	
	public static Image createExamBackground(int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		  Graphics2D tGraphics2D = bi.createGraphics(); //create a graphics object to paint to
		  tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  tGraphics2D.drawImage(i, 0, 0, width, height, null ); //draw the image scaled
		  tGraphics2D.drawImage(i2, 0, 0, width, height, null ); //draw the image scaled
		  float bri = (float) brightness.getValue();
		  bi = new RescaleOp(new float[]{bri,bri,bri,bri}, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, null).filter(bi, null);
		return bi;
	}

	public static Image createExamBackground(int width) {
		int height = (int) ((((double)width)/i.getWidth())*i.getHeight());
		return createExamBackground(width, height);
	}
	
}
