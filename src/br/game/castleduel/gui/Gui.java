package br.game.castleduel.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final int HEIGHT = 200;
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void init(int width) {
		setPreferredSize(new Dimension(width, HEIGHT));
		setSize(getPreferredSize());
		add(new DrawPanel(getSize()));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		protected BufferedImage castle = ImageLoader.load("castle.png");
		protected BufferedImage grass = ImageLoader.load("grass.jpg");
		protected BufferedImage sky = ImageLoader.load("sky.jpg");
		
		public DrawPanel(Dimension d) {
			setPreferredSize(d);
			setSize(d);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawBackground(g);
		}
		
		protected void drawBackground(Graphics g) {
			g.drawImage(sky, 0, 0, null);
			
			for (int x = 0; x < getWidth(); x += grass.getWidth()) {
				g.drawImage(grass, x, 150, null);
			}
			
			g.drawImage(castle, 0, 0, null);
			g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);
		}
	}
}
