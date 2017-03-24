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

public class Gui extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static final int HEIGHT_GAME = 255;
	protected BufferedImage castle = ImageLoader.load("castle.png");
	protected BufferedImage grass = ImageLoader.load("grass.jpg");
	protected BufferedImage sky = ImageLoader.load("sky.jpg");
	private List<SpriteAbstract> deadSprites;

	protected LinkedBlockingQueue<SpriteAbstract> sprites;
	protected Castle castleP1;
	protected Castle castleP2;
	
	public Gui() {}
	
	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			) {

		this.castleP1 = castleP1;
		this.castleP2 = castleP2;
		sprites = new LinkedBlockingQueue<>();
		
		Dimension size = new Dimension(width, HEIGHT_GAME);
		setPreferredSize(size);
		setSize(size);
		deadSprites = new ArrayList<>(20);
		
		JFrame jframe = new JFrame("Castle Duel Challenge");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.add(this);
		jframe.pack();
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
	}
	
	public void addSprite(int player, Unit unit) {
		sprites.add(new SpriteUnit(player, unit));
	}
	
	public void addSpriteAbstract(SpriteAbstract sprite) {
		sprites.add(sprite);
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
			g.drawImage(grass, x, 165, null);
		}
		
		g.drawImage(castle, 0, 0, null);
		g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);
	}
	
	protected void drawUnits(Graphics g) {
		deadSprites.clear();
		for (SpriteAbstract sprite : sprites) {
			sprite.paint(g);
			if (sprite.shouldDelete()) {
				deadSprites.add(sprite);
			}
		}
		for (SpriteAbstract sprite : deadSprites) {
			sprites.remove(sprite);
		}
	}
}
