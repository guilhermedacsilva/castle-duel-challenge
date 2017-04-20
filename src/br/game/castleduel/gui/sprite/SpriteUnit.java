package br.game.castleduel.gui.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import br.game.castleduel.Battleground;
import br.game.castleduel.unit.Unit;
import br.game.castleduel.util.ImageLoader;
import br.game.castleduel.util.ImageUtil;

public class SpriteUnit extends Sprite {
	private static final int UNIT_WIDTH = 17;
	private static final int UNIT_HEIGHT = 23;
	private static final int UNIT_CENTER_X = 8;
	private static final int UNIT_CENTER_Y = 11;
	private static int ID_GENERATOR = 0;
	private static int[] POS_Y_OFFSET = new int[] {0,0}; //p1, p2
	private final int id;
	private final int player;
	private final Unit unit;
	private int width;
	private BufferedImage image;
	private int posY;
	
	public SpriteUnit(int player, Unit unit) {
		this.player = player;
		this.unit = unit;
		width = 50;
		id = ID_GENERATOR++;
		image = loadImage(player, unit);
		
		posY = TYPE_DATA[unit.getType()][POS_Y] + POS_Y_OFFSET[player-1];
		POS_Y_OFFSET[player-1] += 25;
		if (POS_Y_OFFSET[player-1] > 50) {
			POS_Y_OFFSET[player-1] = 0;
		}
		
		unit.setSprite(this);
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
		if (player == 1) {
			return unit.getPosition();
		}
		return Battleground.BATTLEGROUND_WIDTH - unit.getPosition() - width;
	}

	@Override
	public int getPositionY() {
		return posY;
	}
	
	@Override
	public int getWidth() {
		return UNIT_WIDTH;
	}
	
	@Override
	public int getHeight() {
		return UNIT_HEIGHT;
	}
	
	@Override
	public int getCenterX() {
		return UNIT_CENTER_X;
	}
	
	@Override
	public int getCenterY() {
		return UNIT_CENTER_Y;
	}
	
	protected static BufferedImage loadImage(int player, Unit unit) {
		BufferedImage image = ImageLoader.load("unit" + unit.getType() + ".png");
		image = ImageUtil.copy(image);
		if (player == 2) {
			ImageUtil.flip(image);
		}
		return image;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpriteUnit other = (SpriteUnit) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
