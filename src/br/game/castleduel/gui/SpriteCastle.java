package br.game.castleduel.gui;

import java.awt.Graphics;

import br.game.castleduel.Battleground;

public class SpriteCastle extends SpriteAbstract {
	private static SpriteCastle CASTLE1 = new SpriteCastle(1);
	private static SpriteCastle CASTLE2 = new SpriteCastle(2);
	private int positionX;
	private int positionY;
	
	public static SpriteCastle getSprite(int player) {
		if (player == 1) {
			return CASTLE1;
		}
		return CASTLE2;
	}
	
	private SpriteCastle(int player) {
		if (player == 1) {
			positionX = Gui.CASTLE_POS_X;
		} else {
			positionX = Battleground.BATTLEGROUND_WIDTH - Gui.CASTLE_POS_X - Gui.CASTLE_HEALTH_WIDTH;
		}
		positionY = Gui.CASTLE_POS_Y;
	}

	@Override
	public boolean shouldDelete() {
		return false;
	}

	@Override
	public void paint(Graphics g) {
	}

	@Override
	public int getPositionX() {
		return positionX;
	}

	@Override
	public int getPositionY() {
		return positionY;
	}
}
