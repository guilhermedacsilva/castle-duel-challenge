package br.game.castleduel.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class SpriteExplosion extends SpriteAbstract {
	private static final BufferedImage EXPLOSION_IMAGE = ImageLoader.load("explosion.png");
	// the duration (frames) of each state
	private static final int STATE_DURATION_FRAMES = 2;
	private static final int LAST_STATE = 15;
	private static final int STATE_WIDTH_HEIGHT_ORIGINAL = 128;
	private static final int STATE_WIDTH_HEIGHT_SCALED = 128 / 2;
	private static final Random RANDOM = new Random();
	private int currentFrame = -1;
	private int currentState = 0;
	private SpriteAbstract spriteUnit;
	private int offsetX;
	private int offsetY;
	
	public SpriteExplosion(Unit unit) {
		spriteUnit = unit.getSprite();
		offsetX = RANDOM.nextInt(17) - 8;
		offsetY = RANDOM.nextInt(23) - 11;
	}

	public SpriteExplosion(Castle castle) {
		spriteUnit = SpriteCastle.getSprite(castle.getPlayer());
		offsetX = RANDOM.nextInt(111);
		offsetY = RANDOM.nextInt(126);
	}

	@Override
	public void paint(Graphics g) {
		currentFrame++;
		currentState = currentFrame / STATE_DURATION_FRAMES;
		
		if (shouldDelete()) {
			return;
		}
		drawExplosion(g);
	}

	@Override
	public boolean shouldDelete() {
		return currentState > LAST_STATE;
	}
	
	private void drawExplosion(Graphics g) {
		final int x1 = currentState % 4;
		final int y1 = currentState / 4;
		
		int destinationPositionX = spriteUnit.getPositionX() + offsetX;
		int destinationPositionY = spriteUnit.getPositionY() + offsetY;
		
		g.drawImage(EXPLOSION_IMAGE, 
				destinationPositionX, 
				destinationPositionY, 
				destinationPositionX + STATE_WIDTH_HEIGHT_SCALED, 
				destinationPositionY + STATE_WIDTH_HEIGHT_SCALED, 
				STATE_WIDTH_HEIGHT_ORIGINAL * x1, 
				STATE_WIDTH_HEIGHT_ORIGINAL * y1, 
				STATE_WIDTH_HEIGHT_ORIGINAL * x1 + STATE_WIDTH_HEIGHT_ORIGINAL - 1, 
				STATE_WIDTH_HEIGHT_ORIGINAL * y1 + STATE_WIDTH_HEIGHT_ORIGINAL - 1, 
				null);
		
	}
	
	// NOT USED

	@Override
	public int getPositionX() {
		return 0;
	}

	@Override
	public int getPositionY() {
		return 0;
	}
	
}
