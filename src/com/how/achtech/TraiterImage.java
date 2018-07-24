package com.how.achtech;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class TraiterImage {
	private static final int LOGO_WIDTH = 512;
	private static final int LOGO_HEIGHT = 512;
	private static final int BACKGROUND_WIDTH = 1024;
	private static final int BACKGROUND_HEIGHT = 500;
	
	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage,boolean isLogo,boolean toLabel){
		int width = isLogo ? LOGO_WIDTH : BACKGROUND_WIDTH;
		int height = isLogo ? LOGO_HEIGHT : BACKGROUND_HEIGHT;
		
		if(toLabel){
			width = width/2;
			height = height/2;
		}
		try{
		BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
			
		return resizedImage;
		} catch (java.lang.IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Erreur dans URL du photo");
		}
		return null;
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public static BufferedImage makeBorder(BufferedImage img, Color borderColor) {
		int w = img.getWidth();
        int h = img.getHeight();
        int eps = 10;
        //TOP
        for (int x = 0; x < w ; x++) {
            for (int y = 0; y < eps; y++) {
                img.setRGB(x, y, borderColor.getRGB());
            }
        }
        
        //LEFT
        for (int x = 0; x < h ; x++) {
            for (int y = 0; y < eps; y++) {
                img.setRGB(y, x, borderColor.getRGB());
            }
        }

        //BOTTOM
        for (int x = 0; x < w ; x++) {
            for (int y = h-1; y > h-eps-1; y--) {
                img.setRGB(x, y, borderColor.getRGB());
            }
        }
        
        //LEFT
        for (int x = w-1; x > w-1-eps ; x--) {
            for (int y = 0; y < h; y++) {
                img.setRGB(x, y, borderColor.getRGB());
            }
        }

        // TOP RIGHT
        int rounded = 75;
        for (int x = 0,y = rounded; x < rounded && y > 0; y--) {
            for (int k = 0; k < eps ; k++) {
            	img.setRGB(x+k, y, borderColor.getRGB());
            }
            x=y%5==0?x:++x;
        }

        return img;
	}
}
