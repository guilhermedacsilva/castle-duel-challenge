package br.game.castleduel.gui.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.game.castleduel.util.ImageLoader;

public class SpriteExplosion extends Sprite {
	private static final BufferedImage EXPLOSION_IMAGE = ImageLoader.load("explosion.png");
	private static final int STATE_DURATION_FRAMES = 2;
	private static final int LAST_STATE = 15;
	private static final int STATE_WIDTH_HEIGHT_ORIGINAL = 128;
	private static final int STATE_WIDTH_HEIGHT_SCALED = 128 / 2;
	private static final Random RANDOM = new Random();
	private int currentFrame = -1;
	private int currentState = 0;
	private Sprite target;
	private int offsetX;
	private int offsetY;
	
	public SpriteExplosion(Sprite target) {
		this.target = target;
		offsetX = RANDOM.nextInt(target.getWidth()/3) + target.getWidth()/3 - 32;
		offsetY = RANDOM.nextInt(target.getHeight()/3) + target.getHeight()/3 - 32;
	}

	@Override
	public void paint(Graphics g) {
		currentFrame++;
		currentState = currentFrame / STATE_DURATION_FRAMES;
		
		if (shouldDelete()) {
			return;
		}
		paintExplosion(g);
	}

	@Override
	public boolean shouldDelete() {
		return currentState > LAST_STATE;
	}
	
	private void paintExplosion(Graphics g) {
		final int x1 = currentState % 4;
		final int y1 = currentState / 4;
		
		int destinationPositionX = target.getPositionX() + offsetX;
		int destinationPositionY = target.getPositionY() + offsetY;
		
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
}
