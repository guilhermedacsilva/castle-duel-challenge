package br.game.castleduel.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import br.game.castleduel.resources.Resources;

public class ImageLoader {
	private static Map<String, BufferedImage> IMAGE_CACHE =
			new HashMap<>();
	
	public static BufferedImage load(String name) {
		if (IMAGE_CACHE.containsKey(name)) {
			return IMAGE_CACHE.get(name);
		}
		BufferedImage image = null;
		try {
			image = loadImageFromJar(name);
		} catch (IOException e) {
			System.out.println("Error. Contact the admin.\n");
			System.exit(1);
		}
		IMAGE_CACHE.put(name, image);
		return image;
	}
	
	private static BufferedImage loadImageFromJar(String name) 
			throws IOException {
		InputStream input = Resources.class.getResourceAsStream(name);
		return ImageIO.read(input);
	}
	
}
