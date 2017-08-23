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

import javax.swing.JPanel;

import br.game.castleduel.Battleground;
import br.game.castleduel.gui.sprite.Sprite;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.util.ImageLoader;
import br.game.castleduel.util.SimpleJFrame;

public class WindowGui extends JPanel implements GuiInterface {
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT_GAME = 395;
	public static final int CASTLE_POS_X = 20;
	public static final int CASTLE_POS_Y = 10;
	public static final int CASTLE_HEALTH_WIDTH = 165;
	public static final int CASTLE_HEALTH_HEIGHT = 10;
	
	private BufferedImage castle = ImageLoader.load("castle.png");
	private BufferedImage grass = ImageLoader.load("grass.jpg");
	private BufferedImage sky = ImageLoader.load("sky.jpg");
	
	protected List<Sprite> deadSprites = new ArrayList<>(20);
	protected LinkedBlockingQueue<Sprite> sprites = new LinkedBlockingQueue<>();
	protected String playerName1;
	protected String playerName2;
	protected Castle castleP1;
	protected Castle castleP2;
	protected int playerWon = -1;
	protected int[] gold = new int[2];
	protected int framesLeft;
	
	public WindowGui(String playerName1, String playerName2) {
		if (playerName1 == null) {
			playerName1 = "Player 1";
		}
		if (playerName2 == null) {
			playerName2 = "Player 2";
		}
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
	}
	
	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			) {

		this.castleP1 = castleP1;
		this.castleP2 = castleP2;
		
		Dimension size = new Dimension(width, HEIGHT_GAME);
		setPreferredSize(size);
		setSize(size);
		
		SimpleJFrame.create("Castle Duel Challenge", this);
	}
	
	public void setGold(int goldP1, int goldP2) {
		gold[0] = goldP1;
		gold[1] = goldP2;
	}

	public void setPlayerWon(int player) {
		playerWon = player;
	}

	public void updateGame(int framesLeft) {
		this.framesLeft = framesLeft;
		repaint();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		drawUnits(g);
		drawPlayerWon(g);
	}

	protected void drawBackground(final Graphics g) {
		g.drawImage(sky, 0, 0, null);
		drawGrass(g);
		drawCastleHealth(g);
		drawCastles(g);
		drawNames(g);
		drawGold(g);
		drawTime(g);
	}
	
	private void drawGrass(final Graphics g) {
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < getWidth(); x += grass.getWidth()) {
				g.drawImage(grass, x, 165+(y*128), null);
			}
		}
	}
	
	private void drawCastles(final Graphics g) {
		g.drawImage(castle, 0, 0, null);
		g.drawImage(castle, getWidth() - castle.getWidth(), 0, null);
	}
	
	private void drawNames(final Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		drawOutline(g, playerName1, 110-(playerName1.length()*10), 50, 1);
		drawOutline(g, playerName2, 695-(playerName2.length()*10), 50, 1);
		g.setColor(Color.WHITE);
		g.drawString(playerName1, 110-(playerName1.length()*10), 50);
		g.drawString(playerName2, 695-(playerName2.length()*10), 50);
	}

	private void drawGold(final Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		drawOutline(g, "$"+gold[0], 200, 35, 1);
		drawOutline(g, "$"+gold[1], 530, 35, 1);
		g.setColor(Color.YELLOW);
		g.drawString("$"+gold[0], 200, 35);
		g.drawString("$"+gold[1], 530, 35);
	}
	
	private void drawTime(final Graphics g) {
		drawOutline(g, "Time: "+framesLeft, 310, 35, 1);
		g.setColor(Color.red);
		g.drawString("Time: "+framesLeft, 310, 35);
	}
	
	protected void drawOutline(
			final Graphics g, 
			final String text, 
			final int x, 
			final int y, 
			final int size)
	{
		g.setColor(Color.BLACK);
		for (int i = -size; i <= size; i+=size*2) {
			for (int j = -size; j <= size; j+=size*2) {
				g.drawString(text, x+i, y+j);
			}
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
	
	protected void drawUnits(Graphics g) {
		deadSprites.clear();
		for (Sprite sprite : sprites) {
			sprite.paint(g);
			if (sprite.shouldDelete()) {
				deadSprites.add(sprite);
			}
		}
		for (Sprite sprite : deadSprites) {
			sprites.remove(sprite);
		}
	}

	protected void drawPlayerWon(Graphics g) {
		if (playerWon != -1) {
			String printString = "PLAYER " + playerWon + " WON!";
			if (playerWon == 0) {
				printString = "DRAW!";
			}
			g.setFont(new Font(Font.SERIF, Font.BOLD, 90));
			drawOutline(g, printString, 10, 150, 5);
			g.setColor(Color.white);
			g.drawString(printString, 10, 150);
		}
	}
}
