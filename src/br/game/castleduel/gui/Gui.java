package br.game.castleduel.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final int HEIGHT = 230;

	protected LinkedBlockingQueue<UnitSprite> sprites;
	protected Castle castleP1;
	protected Castle castleP2;
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			) {
		setPreferredSize(new Dimension(width, HEIGHT));
		setSize(getPreferredSize());
		add(new DrawPanel(getSize()));
		setVisible(true);
		setLocationRelativeTo(null);
		
		this.castleP1 = castleP1;
		this.castleP2 = castleP2;
		sprites = new LinkedBlockingQueue<>();
	}
	
	public void addSprite(int player, Unit unit) {
		sprites.add(new UnitSprite(player, unit));
	}

	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		protected BufferedImage castle = ImageLoader.load("castle.png");
		protected BufferedImage grass = ImageLoader.load("grass.jpg");
		protected BufferedImage sky = ImageLoader.load("sky.jpg");
		private List<UnitSprite> deadSprites;
		
		public DrawPanel(Dimension d) {
			setPreferredSize(d);
			setSize(d);
			deadSprites = new ArrayList<>(20);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawBackground(g);
			drawUnits(g);
		}

		protected void drawBackground(Graphics g) {
			g.drawImage(sky, 0, 0, null);
			
			for (int x = 0; x < getWidth(); x += grass.getWidth()) {
				g.drawImage(grass, x, 150, null);
			}
			
			g.drawImage(castle, 0, 0, null);
			g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);
		}
		
		protected void drawUnits(Graphics g) {
			deadSprites.clear();
			for (UnitSprite sprite : sprites) {
				sprite.paint(g);
				if (sprite.isUnitDead()) {
					deadSprites.add(sprite);
				}
			}
			for (UnitSprite sprite : deadSprites) {
				sprites.remove(sprite);
			}
		}
	}
}
