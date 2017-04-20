package br.game.castleduel.gui.sprite;

import java.awt.Graphics;

import br.game.castleduel.Battleground;
import br.game.castleduel.gui.WindowGui;

public class SpriteCastle extends Sprite {
	private static final int CASTLE_WIDTH = 111;
	private static final int CASTLE_HEIGHT = 126;
	private static SpriteCastle[] castles = {new SpriteCastle(0), new SpriteCastle(1)};
	private int positionX;
	private int positionY;
	
	public static SpriteCastle getSprite(int playerIndex) {
		return castles[playerIndex];
	}
	
	private SpriteCastle(int playerIndex) {
		if (playerIndex == 0) {
			positionX = WindowGui.CASTLE_POS_X;
		} else {
			positionX = Battleground.BATTLEGROUND_WIDTH - WindowGui.CASTLE_POS_X - WindowGui.CASTLE_HEALTH_WIDTH;
		}
		positionY = WindowGui.CASTLE_POS_Y;
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
	
	@Override
	public int getWidth() {
		return CASTLE_WIDTH;
	}
	
	@Override
	public int getHeight() {
		return CASTLE_HEIGHT;
	}
}
