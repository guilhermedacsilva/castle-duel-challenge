package br.game.castleduel.gui.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import br.game.castleduel.Battleground;
import br.game.castleduel.unit.Unit;
import br.game.castleduel.util.ImageLoader;
import br.game.castleduel.util.ImageUtil;

public class SpriteUnit extends Sprite {
	private static int[] POS_Y_OFFSET = new int[] {0,0}; //p1, p2
	private final int playerIndex;
	private final Unit unit;
	private BufferedImage image;
	private int posY;
	
	public SpriteUnit(int playerIndex, Unit unit) {
		this.playerIndex = playerIndex;
		this.unit = unit;
		image = loadImage(playerIndex, unit);
		posY = TYPE_DATA[unit.getType()][POS_Y] + POS_Y_OFFSET[playerIndex];
		
		nextUnitPositionY(playerIndex);
	}
	
	private static void nextUnitPositionY(int playerIndex) {
		POS_Y_OFFSET[playerIndex] += 75;
		if (POS_Y_OFFSET[playerIndex] > 150) {
			POS_Y_OFFSET[playerIndex] = 0;
		}
	}

	@Override
	public boolean shouldDelete() {
		return unit.isDead();
	}

	@Override
	public void paint(Graphics g) {
		drawUnit(g);
		drawHealthBar(g);
	}
	
	private void drawUnit(Graphics g) {
		g.drawImage(image, 
				getPositionX(), 
				getPositionY(),
				getPositionX() + getImageData(WIDTH),
				getPositionY() + getImageData(HEIGHT),
				getImageData(X1),
				getImageData(Y1),
				getImageData(X2),
				getImageData(Y2),
				null);
	}
	
	private void drawHealthBar(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(getPositionX(), posY-8, getImageData(WIDTH), 5);
		g.setColor(Color.red);
		g.fillRect(getPositionX(), posY-8, (int)(getImageData(WIDTH)*unit.getHealthPercentage()), 5);
	}
	
	private int getImageData(int index) {
		return TYPE_DATA[unit.getType()][index];
	}

	@Override
	public int getPositionX() {
		if (playerIndex == 0) {
			return unit.getPosition() - getImageData(WIDTH);
		}
		return Battleground.BATTLEGROUND_WIDTH - unit.getPosition();
	}

	@Override
	public int getPositionY() {
		return posY;
	}
	
	@Override
	public int getWidth() {
		return getImageData(WIDTH);
	}
	
	@Override
	public int getHeight() {
		return getImageData(HEIGHT);
	}
	
	@Override
	public int getCenterX() {
		return getWidth()/2;
	}
	
	@Override
	public int getCenterY() {
		return getHeight()/2;
	}
	
	protected static BufferedImage loadImage(int playerIndex, Unit unit) {
		BufferedImage image = ImageLoader.load("unit" + unit.getType() + ".png");
		image = ImageUtil.copy(image);
		if (playerIndex == 1) {
			ImageUtil.flip(image);
		}
		return image;
	}
	
	
}
