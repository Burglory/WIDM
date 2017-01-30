package com.gynt.widm.graphics.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.gynt.widm.core.Settings;
import com.gynt.widm.core.Settings.PreferenceItem;
import com.gynt.widm.io.Serialization;

public class ImageGenerator {

	private static BufferedImage i;
	private static BufferedImage i2;
	private static PreferenceItem brightness;
	private static ImageIcon examicon;
	private static ImageIcon choiceicon;
	private static ImageIcon choiceparticon;

	static{
		try {
			i = ImageIO.read(Serialization.LOADER.getResourceAsStream("resources/images/fingerprint.jpg"));
			i2 = ImageIO.read(Serialization.LOADER.getResourceAsStream("resources/images/de_mol.png"));
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

	public static ImageIcon getExamIcon() {
		if(examicon!=null) return examicon;
		int width=16;
		int height = 16;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		 Graphics2D g = bi.createGraphics(); //create a graphics object to paint to
		  g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  g.setColor(Color.gray);
		  g.setStroke(new BasicStroke(2));
		  int i=1;
		  while(i<height) {
			  g.drawLine(0, i, width, i);
			  i=i+4;
		  }
		examicon = new ImageIcon(bi);
		return examicon;
	}

	public static ImageIcon getChoiceIcon() {
		if(choiceicon!=null) return choiceicon;
		int width=16;
		int height = 16;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		 Graphics2D g = bi.createGraphics(); //create a graphics object to paint to
		  g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  g.setColor(Color.gray);
		  //g.setStroke(new BasicStroke(2));
		  g.fillOval(4, 4, 8, 8);
		  choiceicon = new ImageIcon(bi);
		return choiceicon;
	}

	public static ImageIcon getChoicePartIcon() {
		if(choiceparticon!=null) return choiceparticon;
		int width=16;
		int height = 16;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		 Graphics2D g = bi.createGraphics(); //create a graphics object to paint to
		  g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  g.setColor(Color.gray);
		  //g.setStroke(new BasicStroke(2));
		  g.fillOval(4, 1, 4, 4);
		  g.fillOval(4, 6, 4, 4);
		  g.fillOval(4, 11, 4, 4);
		  choiceparticon = new ImageIcon(bi);
		return choiceparticon;
	}

}
