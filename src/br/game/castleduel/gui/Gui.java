package br.game.castleduel.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final int HEIGHT = 200;
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void init(
			int width,
			List<Unit> unitsP1, 
			List<Unit> unitsP2, 
			Castle castleP1, 
			Castle castleP2
			) {
		setPreferredSize(new Dimension(width, HEIGHT));
		setSize(getPreferredSize());
		add(new DrawPanel(getSize()));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
		// TODO: units moving, HP, castle HP
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
