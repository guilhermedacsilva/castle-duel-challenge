package br.game.castleduel.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage castle = ImageLoader.loadImage("castle.png");
		BufferedImage grass = ImageLoader.loadImage("grass.jpg");
		BufferedImage sky = ImageLoader.loadImage("sky.jpg");
		
		g.drawImage(sky, 0, 0, null);
		
		for (int x = 0; x < getWidth(); x += grass.getWidth()) {
			g.drawImage(grass, x, 150, null);
		}
		
		g.drawImage(castle, 0, 0, null);
		
		g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);
	}
}
