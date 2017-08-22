package br.game.castleduel.gui.sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import br.game.castleduel.util.ImageLoader;

public class SpriteFireball extends Sprite {
	private static final Random RANDOM = new Random();
	private static final BufferedImage IMAGE = ImageLoader.load("fireball.png");
	private static final float MAX_FRAME = 15;
	private int currentFrame = 0;
	private int x1, y1, x2, y2;

	public SpriteFireball(Sprite caster, Sprite target) {
		x1 = caster.getPositionX();
		y1 = caster.getPositionY() + target.getHeight()/4;
		x2 = target.getPositionX() + RANDOM.nextInt(target.getWidth()/3) + target.getWidth()/3;
		y2 = target.getPositionY() + RANDOM.nextInt(target.getHeight()/2) + target.getHeight()/4;
	}

	@Override
	public void paint(Graphics g) {
		currentFrame++;
		
		if (shouldDelete()) {
			return;
		}
		
		int x = (int) (x1 + (x2 - x1) * (currentFrame / MAX_FRAME));
		int y = (int) (y1 + (y2 - y1) * (currentFrame / MAX_FRAME));

		g.drawImage(IMAGE, x, y, x + 18, y + 17, 0, 0, 18, 17, null);
	}

	@Override
	public boolean shouldDelete() {
		return currentFrame > MAX_FRAME;
	}	
	
}
