package br.game.castleduel.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.game.castleduel.Battleground;
import br.game.castleduel.Game;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class NormalGui extends JPanel implements GuiInterface {
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT_GAME = 255;
	public static final int CASTLE_POS_X = 20;
	public static final int CASTLE_POS_Y = 10;
	public static final int CASTLE_HEALTH_WIDTH = 165;
	public static final int CASTLE_HEALTH_HEIGHT = 10;
	protected BufferedImage castle = ImageLoader.load("castle.png");
	protected BufferedImage grass = ImageLoader.load("grass.jpg");
	protected BufferedImage sky = ImageLoader.load("sky.jpg");
	private List<SpriteAbstract> deadSprites;

	protected LinkedBlockingQueue<SpriteAbstract> sprites;
	protected Castle castleP1;
	protected Castle castleP2;
	protected int playerWon = -1;
	protected int[] gold = new int[2];
	
	public NormalGui() {}
	
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
	
	public void setGold(int goldP1, int goldP2) {
		gold[0] = goldP1;
		gold[1] = goldP2;
	}

	public void setPlayerWon(int player) {
		playerWon = player;
	}

	public void updateGame() {
		repaint();
		Toolkit.getDefaultToolkit().sync();
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
		drawPlayerWon(g);
	}

	protected void drawBackground(Graphics g) {
		g.drawImage(sky, 0, 0, null);
		
		for (int x = 0; x < getWidth(); x += grass.getWidth()) {
			g.drawImage(grass, x, 165, null);
		}
		
		drawCastleHealth(g);
		
		g.drawImage(castle, 0, 0, null);
		g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);

		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		drawOutline(g, "$"+gold[0], 200, 35);
		drawOutline(g, "$"+gold[1], 530, 35);
		g.setColor(Color.YELLOW);
		g.drawString("$"+gold[0], 200, 35);
		g.drawString("$"+gold[1], 530, 35);
		
		final int framesLeft = (Game.FRAME_LIMIT - Game.getCURRENT_FRAME()) / 100;
		drawOutline(g, "Time: "+framesLeft, 310, 35);
		g.setColor(Color.red);
		g.drawString("Time: "+framesLeft, 310, 35);
	}
	
	protected void drawOutline(Graphics g, String text, int x, int y) {
		g.setColor(Color.BLACK);
		for (int i = -1; i < 2; i+=2) {
			for (int j = -1; j < 2; j+=2) {
				g.drawString(text, x+i, y+j);
			}
		}
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

	protected void drawCastleHealth(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(CASTLE_POS_X, 
				CASTLE_POS_Y, 
				CASTLE_HEALTH_WIDTH, 
				CASTLE_HEALTH_HEIGHT);
		g.fillRect(Battleground.BATTLEGROUND_WIDTH - CASTLE_POS_X - CASTLE_HEALTH_WIDTH, 
				CASTLE_POS_Y, 
				CASTLE_HEALTH_WIDTH, 
				CASTLE_HEALTH_HEIGHT);
		g.setColor(Color.red);
		g.fillRect(CASTLE_POS_X, 
				CASTLE_POS_Y, 
				(int)(CASTLE_HEALTH_WIDTH * castleP1.getHealthPercentage()), 
				CASTLE_HEALTH_HEIGHT);
		g.fillRect(Battleground.BATTLEGROUND_WIDTH - CASTLE_POS_X - CASTLE_HEALTH_WIDTH, 
				CASTLE_POS_Y, 
				(int)(CASTLE_HEALTH_WIDTH * castleP2.getHealthPercentage()), 
				CASTLE_HEALTH_HEIGHT);
	}

	protected void drawPlayerWon(Graphics g) {
		if (playerWon == -1) {
			return;
		}
		
		String printString = "PLAYER " + playerWon + " WON!";
		if (playerWon == 3) {
			printString = "DRAW!";
		}
		g.setFont(new Font(Font.SERIF, Font.BOLD, 90));
		g.setColor(Color.black);
		g.drawString(printString, 10-5, 150-5);
		g.drawString(printString, 10-5, 150+5);
		g.drawString(printString, 10+5, 150-5);
		g.drawString(printString, 10+5, 150+5);
		g.setColor(Color.white);
		g.drawString(printString, 10, 150);
	}
}
