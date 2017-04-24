package br.game.castleduel.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageUtil {
	public static void flip(BufferedImage image)
	{
		int tmp;
	    for (int i = 0; i < image.getWidth()/2; i++)
	        for (int j = 0; j < image.getHeight(); j++)
	        {
	            tmp = image.getRGB(i, j);
	            image.setRGB(i, j, image.getRGB(image.getWidth()-1-i, j));
	            image.setRGB(image.getWidth()-1-i, j, tmp);
	        }
	}
	
	public static BufferedImage copy(BufferedImage image) {
		 final ColorModel cm = image.getColorModel();
		 final boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 final WritableRaster raster = image.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}
